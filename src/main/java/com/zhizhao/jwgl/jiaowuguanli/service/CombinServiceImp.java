package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.Week;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJiXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.bukejilu.BuKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.*;
import com.zhizhao.jwgl.jiaowuguanli.domain.dianmingjilu.DianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiLiShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.*;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.MyDateUtil;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CombinServiceImp implements CombineService {
    @Autowired
    BanJiPaiKeXinXiService banJiPaiKeXinXiService;

    @Autowired
    PaiKeJiLuService paiKeJiLuService;

    @Autowired
    ZhangHaoService zhangHaoService;

    @Autowired
    XueYuanService xueYuanService;

    @Autowired
    BanJiService banJiService;

    @Autowired
    XueYuanKeChengService xueYuanKeChengService;

    @Autowired
    JiaoFeiJiLuService jiaoFeiJiLuService;

    @Autowired
    DianMingJiLuService dianMingJiLuService;

    @Autowired
    BuKeJiLuService buKeJiLuService;

    /**
     * 创建班级排课信息
     * @param cmd
     */
    @Transactional
    @Override
    public void chuangJianBanJiPaiKeXinXi(BanJiPaiKeXinXi.ChuangJianCommand cmd) {
        // 创建班级排课信息
        Long paikeXinXiId = banJiPaiKeXinXiService.chuangJianBanJiPaiKeXinXi(cmd);
        // 创建班级排课信息 end

        // 获取班级详情 start
        DtoBanJi banJi = banJiService.huoQuBanJiXiangQing(cmd.getBanJiId());
        // 获取班级详情 end

        // 创建排课记录 start
        PaiKeGuiZe paiKeGuiZe = cmd.getPaiKeGuiZe();
        //待添加的排课记录列表
        List<PaiKeJiLu> newPaiKeJiLuList = new ArrayList<>();

        // 排课记录，上课学员
        Set<ShangKeXueYuan> shangKeXueYuanSet = new HashSet<>();
        for(BanJiXueYuan banJiXuYuan : banJi.getBanJiXueYuanZu()) {
            ShangKeXueYuan shangKeXueYuan = new ShangKeXueYuan();
            shangKeXueYuan.setXueYuanId(banJiXuYuan.getXueYuanId());
            shangKeXueYuan.setShangKeXueYuanLeiXing(ShangKeXueYuanLeiXing.BEN_BAN);
            shangKeXueYuan.setIsDeleted(false);
            shangKeXueYuanSet.add(shangKeXueYuan);
        }
        // 默认授课课时
        Double moRenShouKeKeShi = banJi.getMoRenShouKeKeShi();
        // 排课方式
        PaiKeFangShi paiKeFangShi = paiKeGuiZe.getPaiKeFangShi();
        // 上课老师Id
        Long shangKeLaoShiId = cmd.getShangKeLaoShiId();
        // 上课教室Id
        Long shangKeJiaoShiId = cmd.getShangKeJiaoShiId();
        // 上课内容
        String shangKeNeiRong = cmd.getShangKeNeiRong();
        // 日历排课 start
        if(paiKeFangShi.equals(PaiKeFangShi.RI_LI_PAI_KE)) {
            Set<PaiKeShangKeShiJian> shangKeShiJianSet = paiKeGuiZe.getPaiKeShangKeShiJianZu();
            PaiKeShangKeShiJian paiKeShangKeShiJian = shangKeShiJianSet.iterator().next();
            Long shangKeKaiShiShiJian = paiKeShangKeShiJian.getStartTime();
            Long shangKeJieShuShiJian = paiKeShangKeShiJian.getStopTime();
            for(Long shangKeRiQi: paiKeGuiZe.getRiLiShangKeRiQi()) {
                PaiKeJiLu paiKeJiLu = generatePaiKeJiLu(paikeXinXiId, shangKeRiQi, shangKeLaoShiId, shangKeJiaoShiId, shangKeKaiShiShiJian, shangKeJieShuShiJian, moRenShouKeKeShi, shangKeNeiRong, shangKeXueYuanSet);
                newPaiKeJiLuList.add(paiKeJiLu);
            }
        }
        // 日历排课 end

        // 规则排课 start
        if(paiKeFangShi.equals(PaiKeFangShi.GUI_ZE_PAI_KE)) {
            // 规则排课，排课开始时间
            Long paiKeStartDate = paiKeGuiZe.getGuiZeKaiShiRiQi();
            // 规则排课，排课结束时间；按次数结束排课时，为空
            Long paiKeEndDate;
            // 规则排课，排课次数；按日期结束时，为空
            int paiKeCiShu;
            //排课上课时间
            Set<PaiKeShangKeShiJian> paiKeShangKeShiJianSet = paiKeGuiZe.getPaiKeShangKeShiJianZu();

            if(paiKeGuiZe.getGuiZeChongFuFangShi().equals(GuiZeChongFuFangShi.MEI_TIAN)) {
                // 每天重复
                // 上课时间为Set的第一个元素
                PaiKeShangKeShiJian paiKeShangKeShiJian = paiKeShangKeShiJianSet.iterator().next();
                Long shangKeKaiShiShiJian = paiKeShangKeShiJian.getStartTime();
                Long shangKeJieShuShiJian = paiKeShangKeShiJian.getStopTime();
                List<Long> paiKeRiQiList;
                if(paiKeGuiZe.getGuiZeJiShuFangShi().equals(GuiZeJiShuFangShi.RI_QI_JIE_SHU)) {
                    // 按日期结束
                    paiKeEndDate = paiKeGuiZe.getGuiZeJieShuRiQi();
                    paiKeRiQiList = MyDateUtil.getEveryDatesByBetween(paiKeStartDate, paiKeEndDate);
                } else {
                    // 按次数结束
                    paiKeCiShu = paiKeGuiZe.getGuiZePaiKeCiShu().intValue();
                    paiKeRiQiList = MyDateUtil.getFutureDatesByStartAndTimes(paiKeStartDate, paiKeCiShu);
                }

                if(paiKeRiQiList.size() == 0) {
                    throw new BusinessException("当前排课方式，无可用排课日期");
                }
                for(Long paikeRiQi: paiKeRiQiList) {
                    PaiKeJiLu paiKeJiLu = generatePaiKeJiLu(paikeXinXiId, paikeRiQi, shangKeLaoShiId, shangKeJiaoShiId, shangKeKaiShiShiJian, shangKeJieShuShiJian, moRenShouKeKeShi, shangKeNeiRong, shangKeXueYuanSet);
                    newPaiKeJiLuList.add(paiKeJiLu);
                }
            } else {
                // 每周重复
                List<Long> paiKeRiQiList;
                for(PaiKeShangKeShiJian paiKeShangKeShiJian : paiKeShangKeShiJianSet) {
                    PaiKeShangKeTian paiKeShangKeTian = paiKeShangKeShiJian.getPaiKeShangKeTian();
                    Long shangKeKaiShiShiJian = paiKeShangKeShiJian.getStartTime();
                    Long shangKeJieShuShiJian = paiKeShangKeShiJian.getStopTime();
                    Week week = Converter.convertPaiKeShangKeTian2Week(paiKeShangKeTian);
                    if(week == null) {throw new BusinessException("无法转换星期几");}
                    if(paiKeGuiZe.getGuiZeJiShuFangShi().equals(GuiZeJiShuFangShi.RI_QI_JIE_SHU)) {
                        // 按日期结束
                        paiKeEndDate = paiKeGuiZe.getGuiZeJieShuRiQi();
                        paiKeRiQiList = MyDateUtil.getFutureDatesByBetweenAndWeek(paiKeStartDate, paiKeEndDate, week, new ArrayList<>());
                    } else {
                        // 按次数结束
                        paiKeCiShu = paiKeGuiZe.getGuiZePaiKeCiShu().intValue();
                        paiKeRiQiList = MyDateUtil.getFutureDatesByStartAndWeekAndTimes(paiKeStartDate, week, paiKeCiShu, new ArrayList<>());
                    }
                    if(paiKeRiQiList.size() == 0) {
                        throw new BusinessException("当前排课方式，无可用排课日期");
                    }
                    for(Long paikeRiQi: paiKeRiQiList) {
                        PaiKeJiLu paiKeJiLu = generatePaiKeJiLu(paikeXinXiId, paikeRiQi, shangKeLaoShiId, shangKeJiaoShiId, shangKeKaiShiShiJian, shangKeJieShuShiJian, moRenShouKeKeShi, shangKeNeiRong, shangKeXueYuanSet);
                        newPaiKeJiLuList.add(paiKeJiLu);
                    }
                }
            }
        }
        // 规则排课 end

        // 保存排课记录
        paiKeJiLuService.saveAllPaiKeJiLu(newPaiKeJiLuList);
    }

    /**
     * 生成排课记录
     * @param banJiPaiKeXinXiId 排课信息Id
     * @param shangKeRiQi 上课日期（排课日期）
     * @param shangKeLaoShiId 上课老师id
     * @param shangKeJiaoShiId 上课教室id
     * @param shangKeShiJianStart 上课开始时间
     * @param shangKeShiJianEnd 上课结束时间
     * @param shouKeKeShi 授课课时
     * @param shangKeNeiRong 上课内容
     * @param shangKeXueYuanZu 上课学员组
     * @return 排课记录
     */
    private PaiKeJiLu generatePaiKeJiLu(Long banJiPaiKeXinXiId, Long shangKeRiQi, Long shangKeLaoShiId,
                                        Long shangKeJiaoShiId, Long shangKeShiJianStart, Long shangKeShiJianEnd,
                                        Double shouKeKeShi, String shangKeNeiRong, Set<ShangKeXueYuan> shangKeXueYuanZu) {
        PaiKeJiLu.ChuangJianCmd chuangJianCmd = new PaiKeJiLu.ChuangJianCmd();
        Long id = SnowflakeIdUtil.nextId();
        chuangJianCmd.setId(id);
        chuangJianCmd.setBanJiPaiKeXinXiId(banJiPaiKeXinXiId);
        chuangJianCmd.setShangKeRiQi(shangKeRiQi);
        chuangJianCmd.setShangKeLaoShiId(shangKeLaoShiId);
        chuangJianCmd.setShangKeJiaoShiId(shangKeJiaoShiId);
        chuangJianCmd.setShangKeShiJianStart(shangKeShiJianStart);
        chuangJianCmd.setShangKeShiJianEnd(shangKeShiJianEnd);
        chuangJianCmd.setShouKeKeShi(shouKeKeShi);
        chuangJianCmd.setShangKeNeiRong(shangKeNeiRong);
        chuangJianCmd.setPaiKeJiLuZhuangTai(PaiKeJiLuZhuangTai.DAI_DIAN_MING);
        chuangJianCmd.setShangKeXueYuanZu(shangKeXueYuanZu);
        PaiKeJiLu paiKeJiLu = PaiKeJiLu.chuangJian(chuangJianCmd);
        return paiKeJiLu;
    }

    /**
     * 创建学员
     * @param dtoXueYuan
     * @return
     */
    @Transactional
    @Override
    public Long chuangJianXueYuan(DtoXueYuan dtoXueYuan) {
        if(dtoXueYuan.getXingMing() == null) {
            throw new BusinessException("请指定学员姓名");
        }
        if(dtoXueYuan.getZhangHaoId() != null) {
            throw new BusinessException("学员账户已存在");
        }

        // 查询学员手机对应的账号是不是存在（一个手机号的账号，可能对应多个学员）
        ZhangHao zhangHao = zhangHaoService.getZhangHaoByShouJiAndLeiXing(dtoXueYuan.getZhangHaoShouJi(), ZhangHaoLeiXing.XUE_YUAN);
        if(zhangHao != null) {
            dtoXueYuan.setZhangHaoId(zhangHao.getId());
        } else {
            // 创建学员账号
            ZhangHao.ChuangJianCmd cmd = new ZhangHao.ChuangJianCmd();
            cmd.setShouJi(dtoXueYuan.getZhangHaoShouJi());
            cmd.setZhangHaoLeiXing(ZhangHaoLeiXing.XUE_YUAN);
            cmd.setXingMing("家长");
            cmd.setIsLaoShi(false);
            Long zhangHaoId = zhangHaoService.chuangJianZhangHao(cmd);
            dtoXueYuan.setZhangHaoId(zhangHaoId);
        }
        // 查询学员手机对应的账号是不是存在（一个手机号的账号，可能对应多个学员） end

        // 创建学员
        XueYuan.ChuangJianCmd xueYuanCmd = new XueYuan.ChuangJianCmd();
        xueYuanCmd.setZhangHaoId(dtoXueYuan.getZhangHaoId());
        xueYuanCmd.setXingMing(dtoXueYuan.getXingMing());
        xueYuanCmd.setXueYuanZhuangTai(dtoXueYuan.getXueYuanZhuangTai());
        xueYuanCmd.setTouXiang(dtoXueYuan.getTouXiang());
        xueYuanCmd.setXingBie(dtoXueYuan.getXingBie());
        xueYuanCmd.setNanLing(dtoXueYuan.getNanLing());
        xueYuanCmd.setJiuDuXueXiao(dtoXueYuan.getJiuDuXueXiao());
        xueYuanCmd.setDanqQianNianJi(dtoXueYuan.getDanqQianNianJi());
        xueYuanCmd.setJiaTingZhuZhi(dtoXueYuan.getJiaTingZhuZhi());
        xueYuanCmd.setXueYuanLaiYuan(dtoXueYuan.getXueYuanLaiYuan());
        xueYuanCmd.setBeiZhuXinXi(dtoXueYuan.getBeiZhuXinXi());
        xueYuanCmd.setGenJinRenId(dtoXueYuan.getGenJinRenId());
        Long xueYuanId = xueYuanService.chuangJianXueYuan(xueYuanCmd);
        // 创建学员 end

        return xueYuanId;
    }

    /**
     * 学员报名
     * @param dtoXueYuanBaoMing 学员报名dto
     */
    @Transactional
    @Override
    public void xueYuanBaoMing(DtoXueYuanBaoMing dtoXueYuanBaoMing) {
        // 提交的学员信息
        DtoXueYuan dtoXueYuan = dtoXueYuanBaoMing.getXueYuanXinXi();
        if(dtoXueYuan == null) {
            throw new BusinessException("学员信息不能为空");
        }
        if(dtoXueYuan.getZhangHaoShouJi() == null) {
            throw new BusinessException("学员手机不能为空");
        }
        // 提交的学员课程列表
        List<DtoXueYuanKeCheng> dtoXueYuanKeChengList = dtoXueYuanBaoMing.getXueYuanKeChengList();
        if(dtoXueYuanKeChengList == null || dtoXueYuanKeChengList.size() == 0) {
            throw new BusinessException("请至少选择一门报名课程");
        }
        // 提交的缴费历史
        JiaoFeiLiShi jiaoFeiLiShi = dtoXueYuanBaoMing.getJiaoFeiLiShi();
        if(jiaoFeiLiShi == null) {
            throw new BusinessException("缴费内容不能为空");
        }
        Set<JiaoFeiLiShi> jiaoFeiLiShiSet = new HashSet<>();
        jiaoFeiLiShiSet.add(jiaoFeiLiShi);

        // 提交的缴费记录跟进人Id
        Long jiaoFeiJiLuGenJinRenId = dtoXueYuanBaoMing.getGenJinRenId();
        // 提交的学员课程有效期
        Long keChengYouXiaoQi = dtoXueYuanBaoMing.getKeChengYouXiaoQi();

        Long xueYuanId;
        // 学员Id和所属账号Id都为空，说明是新增学员
        if(dtoXueYuan.getId() == null && dtoXueYuan.getZhangHaoId() == null) {
            // 学员状态为在读学员
            dtoXueYuan.setXueYuanZhuangTai(XueYuanZhuangTai.ZAI_DU);
            xueYuanId = chuangJianXueYuan(dtoXueYuan);
        } else if(dtoXueYuan.getId() != null && dtoXueYuan.getZhangHaoId() == null ) {
            throw new BusinessException("请确认系统中是否已存在该学员");
        } else if(dtoXueYuan.getId() == null && dtoXueYuan.getZhangHaoId() != null) {
            throw new BusinessException("请确认系统中是否已存在该学员");
        } else {
            xueYuanId = dtoXueYuan.getId();
            // 更新学员状态为在读学员
            Optional<XueYuan> xueYuanOptional = xueYuanService.huoQuXueYuanById(xueYuanId);
            if(!xueYuanOptional.isPresent()) {
                throw new BusinessException("未找到指定的学员");
            }
            XueYuan xueYuan = xueYuanOptional.get();
            xueYuan.gengGaiXueYuanZhuangTai(XueYuanZhuangTai.ZAI_DU);
            xueYuanService.gengXinXueYuan(xueYuan);
        }

        List<XueYuanKeCheng> xueYuanKeChengList = new ArrayList<>();
        // 生成学员课程
        for(DtoXueYuanKeCheng dtoXueYuanKeCheng: dtoXueYuanKeChengList) {
            if(dtoXueYuanKeCheng.getKeChengId() == null) {
                throw new BusinessException("请指定所选课程");
            }
            if(dtoXueYuanKeCheng.getKeChengShuLiang() == null || dtoXueYuanKeCheng.getZengSongKeShi() == null) {
                throw new BusinessException("课程总数不能为空");
            }
            XueYuanKeCheng.ChuangJianCmd cmd = new XueYuanKeCheng.ChuangJianCmd();
            Long id = SnowflakeIdUtil.nextId();
            cmd.setId(id);
            cmd.setXueYuanId(xueYuanId);
            cmd.setKeChengId(dtoXueYuanKeCheng.getKeChengId());
            cmd.setDingJiaBiaoZhun(dtoXueYuanKeCheng.getDingJiaBiaoZhun());
            cmd.setKeChengZhuangTai(XueYuanKeChengZhuangTai.DAI_QUE_REN);
            cmd.setKeChengLeiXing(dtoXueYuanKeCheng.getKeChengLeiXing());
            cmd.setDanJia(dtoXueYuanKeCheng.getDanJia());
            cmd.setKeChengShuLiang(dtoXueYuanKeCheng.getKeChengShuLiang());
            cmd.setZengSongKeShi(dtoXueYuanKeCheng.getZengSongKeShi());
            cmd.setYouHuiLeiXing(dtoXueYuanKeCheng.getYouHuiLeiXing());
            cmd.setYouHuiShuLiang(dtoXueYuanKeCheng.getYouHuiShuLiang());
            cmd.setKeChengYouXiaoQi(keChengYouXiaoQi);
            cmd.setBeiZhu(dtoXueYuanKeCheng.getBeiZhu());
            cmd.setShengYuKeShi(dtoXueYuanKeCheng.getKeChengShuLiang() + dtoXueYuanKeCheng.getZengSongKeShi());

            XueYuanKeCheng xueYuanKeCheng = XueYuanKeCheng.chaungJian(cmd);
            xueYuanKeChengList.add(xueYuanKeCheng);
        }

        List<XueYuanKeCheng> newXueYuanKeChengList = xueYuanKeChengService.saveAllXueYuanKeCheng(xueYuanKeChengList);

        // 创建缴费记录
        List<Long> xueYuanKeChengIds = new ArrayList<>();
        for(XueYuanKeCheng xueYuanKeCheng: newXueYuanKeChengList) {
            xueYuanKeChengIds.add(xueYuanKeCheng.getId());
        }

        JiaoFeiJiLu.ChuangJianCmd jiaoFeiJiLuCmd = new JiaoFeiJiLu.ChuangJianCmd();
        jiaoFeiJiLuCmd.setXueYuanId(xueYuanId);
        jiaoFeiJiLuCmd.setXueYuanKeChengZu(xueYuanKeChengIds);
        jiaoFeiJiLuCmd.setJiaoFeiJiLuZhuangTai(JiaoFeiJiLuZhuangTai.WEI_JIAO_FEI);
        jiaoFeiJiLuCmd.setGenJinRenId(jiaoFeiJiLuGenJinRenId);
        jiaoFeiJiLuCmd.setJiaoFeiLiShiZu(jiaoFeiLiShiSet);
        jiaoFeiJiLuService.chuangJian(jiaoFeiJiLuCmd);
    }

    /**
     * 学员课程选择班级
     *
     * @param xueYuanId        学员Id
     * @param xueYuanKeChengId 学员课程Id
     * @param targetBanJiId  目标班级Id
     */
    @Transactional
    @Override
    public void xueYuanXuanBan(Long xueYuanId, Long xueYuanKeChengId, Long targetBanJiId) {
        if(xueYuanId == null) {
            throw new BusinessException("请指定选班学员");
        }
        if(xueYuanKeChengId == null) {
            throw new BusinessException("请指定选班课程");
        }
        if(targetBanJiId == null) {
            throw new BusinessException("请指定目标班级");
        }

        // 当前报读的班级Id
        Long currentBanJiId = null;
        DtoXueYuanKeCheng dtoXueYuanKeCheng = xueYuanKeChengService.getXueYuanKeChengById(xueYuanKeChengId);
        if(dtoXueYuanKeCheng == null) {
            throw new BusinessException("当前学员所选课程已结课，或不存在");
        }
        if(dtoXueYuanKeCheng.getShengYuKeShi() <= 0) {
            throw new BusinessException("当前学员所选课程，剩余课时不足");
        }
        if(dtoXueYuanKeCheng.getBanJi() != null) {
            currentBanJiId = dtoXueYuanKeCheng.getBanJi().getId();
        }

        if(currentBanJiId == null) {
            // 学员课程未选择过班级

            // 班级，新增学员
            banJiService.tianJiaBanJiXueYuan(targetBanJiId, xueYuanId);
            // 更改学员课程状态
            xueYuanKeChengService.xuanBanGengGaiKeChengZhuangTai(xueYuanKeChengId);

            // 目标班级的排课记录
            List<PaiKeJiLu> paiKeJiLuList = huoQuPaiKeJiLuList(targetBanJiId);
            // 排课记录添加上课学员
            for(PaiKeJiLu paiKeJiLu : paiKeJiLuList) {
                PaiKeJiLu.TianJiaShangKeXueYuanCmd cmd = new PaiKeJiLu.TianJiaShangKeXueYuanCmd();
                cmd.setIsDeleted(false);
                cmd.setXueYuanId(xueYuanId);
                cmd.setShangKeXueYuanLeiXing(ShangKeXueYuanLeiXing.BEN_BAN);
                paiKeJiLu.tianJiaShangKeXueYuan(cmd);
            }
            paiKeJiLuService.saveAllPaiKeJiLu(paiKeJiLuList);
        } else {
            if(!targetBanJiId.equals(currentBanJiId)) {
                // 新班级，加入学员
                banJiService.tianJiaBanJiXueYuan(targetBanJiId, xueYuanId);
                // 目标班级的排课记录List
                List<PaiKeJiLu> paiKeJiLuList = huoQuPaiKeJiLuList(targetBanJiId);
                // 排课记录添加上课学员
                for(PaiKeJiLu paiKeJiLu : paiKeJiLuList) {
                    PaiKeJiLu.TianJiaShangKeXueYuanCmd cmd = new PaiKeJiLu.TianJiaShangKeXueYuanCmd();
                    cmd.setIsDeleted(false);
                    cmd.setXueYuanId(xueYuanId);
                    cmd.setShangKeXueYuanLeiXing(ShangKeXueYuanLeiXing.BEN_BAN);
                    paiKeJiLu.tianJiaShangKeXueYuan(cmd);
                }

                // 原班级，删除学员
                banJiService.shanChuBanJiXueYuan(currentBanJiId, xueYuanId);
                // 原班级的排课记录List
                List<PaiKeJiLu> paiKeJiLuPreviousList = huoQuPaiKeJiLuList(currentBanJiId);
                // 原班级的排课记录List，删除上课学员
                for(PaiKeJiLu paiKeJiLu : paiKeJiLuPreviousList) {
                    PaiKeJiLu.ShanChuXueYuanCmd cmd = new PaiKeJiLu.ShanChuXueYuanCmd();
                    cmd.setIsDeleted(false);
                    cmd.setXueYuanId(xueYuanId);
                    cmd.setShangKeXueYuanLeiXing(ShangKeXueYuanLeiXing.BEN_BAN);
                    paiKeJiLu.shanChuShanKeXueYuan(cmd);
                }

                // 合并更新后的，目标班级排课记录，原班级排课记录
                List<PaiKeJiLu> allPaiKeJiLuList = new ArrayList<>();
                allPaiKeJiLuList.addAll(paiKeJiLuList);
                allPaiKeJiLuList.addAll(paiKeJiLuPreviousList);

                // 保存
                paiKeJiLuService.saveAllPaiKeJiLu(allPaiKeJiLuList);
            } else {
                throw new BusinessException("不能选择同一班级");
            }
        }
    }

    /**
     * 根据班级Id，获取排课记录列表
     * @param banJiId
     * @return
     */
    private List<PaiKeJiLu> huoQuPaiKeJiLuList(Long banJiId) {
        // 获取班级排课信息
        List<DtoBanJiPaiKeXinXi> dtoBanJiPaiKeXinXiList = banJiPaiKeXinXiService.huoQuBanJiPaiKeXinXi(banJiId);
        List<Long> paiKeXinXiIdList = new ArrayList<>();
        for(DtoBanJiPaiKeXinXi dtoBanJiPaiKeXinXi : dtoBanJiPaiKeXinXiList) {
            paiKeXinXiIdList.add(dtoBanJiPaiKeXinXi.getId());
        }

        // 获取排课记录
        List<PaiKeJiLu> paiKeJiLuList = paiKeJiLuService.getAllByPaiKeXinXinId(paiKeXinXiIdList);
        return paiKeJiLuList;
    }

    /**
     * 缴费记录确认
     *
     * @param jiaoFeiJiLuId        缴费记录Id
     * @param jiaoFeiJiLuZhuangTai 缴费记录状态
     */
    @Transactional
    @Override
    public void jiaoFeiJiLuQueRen(Long jiaoFeiJiLuId, JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai) {
        if(jiaoFeiJiLuId == null) {
            throw new BusinessException("请指定缴费记录");
        }
        if(jiaoFeiJiLuZhuangTai == null) {
            throw new BusinessException("请指定缴费记录状态");
        }
        // 更改缴费记录状态
        jiaoFeiJiLuService.gengGaiJiaoFenJiLuZhuangTai(jiaoFeiJiLuId, jiaoFeiJiLuZhuangTai);
        JiaoFeiJiLu jiaoFeiJiLu = jiaoFeiJiLuService.getById(jiaoFeiJiLuId);
        List<Long> xueYuanKeChengIds = jiaoFeiJiLu.getXueYuanKeChengZu();

        //缴费记录状态说明：
            //未交费：学员已完成报名，但会计未确认收费，此时状态为“未交费”状态，该状态下学员不能加入班级上课。
            //课程状态为：待确认
            //
            //部分缴费：学员已完成报名，但会计只收到缴费金额的一部分，此时状态为“部分缴费”，状态，该状态下学员可以加入班级上课。不论学员报名了多少课程，在“部分缴费”状态下，所有报名的课程均可加入班级。
            //课程状态为：待补缴
            //
            //全部已缴：学员已完成报名，会计确认收到全部缴费金额，此时学员可加入对应报名的课程班级上课。
            //课程状态为： 待排课
        XueYuanKeChengZhuangTai xueYuanKeChengZhuangTai = null;
        if(jiaoFeiJiLuZhuangTai.equals(JiaoFeiJiLuZhuangTai.WEI_JIAO_FEI)) {
            xueYuanKeChengZhuangTai = XueYuanKeChengZhuangTai.DAI_QUE_REN;
        }
        if(jiaoFeiJiLuZhuangTai.equals(JiaoFeiJiLuZhuangTai.BU_FEN_JIAO_FEI)) {
            xueYuanKeChengZhuangTai = XueYuanKeChengZhuangTai.DAI_BU_JIAO;
        }
        if(jiaoFeiJiLuZhuangTai.equals(JiaoFeiJiLuZhuangTai.QUAN_BU_YI_JIAO)) {
            xueYuanKeChengZhuangTai = XueYuanKeChengZhuangTai.DAI_PAI_KE;
        }

        List<XueYuanKeCheng> xueYuanKeChengList = xueYuanKeChengService.getByIds(xueYuanKeChengIds);
        for(XueYuanKeCheng xueYuanKeCheng: xueYuanKeChengList) {
            XueYuanKeCheng.GengGaiKeChengZhuangTaiCmd cmd = new XueYuanKeCheng.GengGaiKeChengZhuangTaiCmd();
            cmd.setKeChengZhuangTai(xueYuanKeChengZhuangTai);
            xueYuanKeCheng.jiaoFeiQueRenGengGaiZhuangTai(cmd);
        }

        xueYuanKeChengService.saveAllXueYuanKeCheng(xueYuanKeChengList);
    }

    /**
     * 排课记录点名
     *
     * @param dtoPaiKeJiLu 排课记录点名信息
     */
    @Transactional
    @Override
    public void paiKeJiLuDianMing(DtoPaiKeJiLu dtoPaiKeJiLu) {
        Long paiKeJiLuId = dtoPaiKeJiLu.getId();
        if(paiKeJiLuId == null) {
            throw new BusinessException("请指定要点名的排课记录");
        }
        // 当前排课记录
        PaiKeJiLu paiKeJiLu = paiKeJiLuService.getPaiKeJiLuById(paiKeJiLuId);
        if(paiKeJiLu == null) {
            throw new BusinessException("对应排课记录未找到");
        }

        // 点名时间，取当前时间
        Date date = new Date();
        Long nowMilli = date.getTime();
        dtoPaiKeJiLu.setDianMingShiJian(nowMilli);

        // 更新排课记录
        Set<DtoShangKeXueYuan> dtoShangKeXueYuanSet = dtoPaiKeJiLu.getShangKeXueYuanZu();
        Set<ShangKeXueYuan> shangKeXueYuanSet = paiKeJiLu.getShangKeXueYuanZu();
        for (DtoShangKeXueYuan dtoShangKeXueYuan : dtoShangKeXueYuanSet) {
             Optional<ShangKeXueYuan> optionalShangKeXueYuan = shangKeXueYuanSet.stream().filter(a -> a.getXueYuanId().equals(dtoShangKeXueYuan.getXueYuanId())).findAny();
             // 如果当前排课记录中未找到该上课学员（前端添加的临时学员或补课学员）
             if(!optionalShangKeXueYuan.isPresent()) {
                 PaiKeJiLu.TianJiaShangKeXueYuanCmd tianJiaShangKeXueYuanCmd = new PaiKeJiLu.TianJiaShangKeXueYuanCmd();
                 tianJiaShangKeXueYuanCmd.setXueYuanId(dtoShangKeXueYuan.getXueYuanId());
                 tianJiaShangKeXueYuanCmd.setIsDeleted(false);
                 tianJiaShangKeXueYuanCmd.setShangKeXueYuanLeiXing(dtoShangKeXueYuan.getShangKeXueYuanLeiXing());
                 tianJiaShangKeXueYuanCmd.setShiTingJiLuId(dtoShangKeXueYuan.getShiTingJiLuId());
                 tianJiaShangKeXueYuanCmd.setBuKeJiLuId(dtoShangKeXueYuan.getBuKeJiLuId());
                 // 添加学员
                 paiKeJiLu.tianJiaShangKeXueYuan(tianJiaShangKeXueYuanCmd);
             }
        }
        // 基本信息
        PaiKeJiLu.GengXinJiBenXinXiCmd gengXinJiBenXinXiCmd = new PaiKeJiLu.GengXinJiBenXinXiCmd();
        // 上课日期
        gengXinJiBenXinXiCmd.setShangKeRiQi(dtoPaiKeJiLu.getShangKeRiQi());
        // 上课老师
        gengXinJiBenXinXiCmd.setShangKeLaoShiId(dtoPaiKeJiLu.getShangKeLaoShiId());
        // 上课教室
        gengXinJiBenXinXiCmd.setShangKeJiaoShiId(dtoPaiKeJiLu.getShangKeJiaoShiId());
        // 上课开始时间
        gengXinJiBenXinXiCmd.setShangKeShiJianStart(dtoPaiKeJiLu.getShangKeShiJianStart());
        // 上课结束时间
        gengXinJiBenXinXiCmd.setShangKeShiJianEnd(dtoPaiKeJiLu.getShangKeShiJianEnd());
        // 授课课时
        gengXinJiBenXinXiCmd.setShouKeKeShi(dtoPaiKeJiLu.getShouKeKeShi());
        // 上课内容
        gengXinJiBenXinXiCmd.setShangKeNeiRong(dtoPaiKeJiLu.getShangKeNeiRong());
        // 排课记录状态
        gengXinJiBenXinXiCmd.setPaiKeJiLuZhuangTai(PaiKeJiLuZhuangTai.YI_DIAN_MING);
        // 点名时间
        gengXinJiBenXinXiCmd.setDianMingShiJian(dtoPaiKeJiLu.getDianMingShiJian());
        paiKeJiLu.gengXinJiBenXinXi(gengXinJiBenXinXiCmd);
        // 更新排课记录 end

        // 班级 - 已授课时
        Double banJiYiShouKeShi = 0.0;

        for(DtoShangKeXueYuan dtoShangKeXueYuan : dtoShangKeXueYuanSet) {
            // 上课学员类型
            ShangKeXueYuanLeiXing shangKeXueYuanLeiXing = dtoShangKeXueYuan.getShangKeXueYuanLeiXing();
            // 学员到课状态
            XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai = dtoShangKeXueYuan.getXueYuanDaoKeZhuangTai();

            // 点名记录创建
            DianMingJiLu.ChuangJianCmd dianMingJiLuChuangJianCmd = new DianMingJiLu.ChuangJianCmd();
            dianMingJiLuChuangJianCmd.setId(SnowflakeIdUtil.nextId());
            dianMingJiLuChuangJianCmd.setPaiKeJiLuId(dtoPaiKeJiLu.getId());
            dianMingJiLuChuangJianCmd.setXueYuanId(dtoShangKeXueYuan.getXueYuanId());
            dianMingJiLuChuangJianCmd.setShangKeXueYuanLeiXing(dtoShangKeXueYuan.getShangKeXueYuanLeiXing());
            dianMingJiLuChuangJianCmd.setXueYuanDaoKeZhuangTai(dtoShangKeXueYuan.getXueYuanDaoKeZhuangTai());
            dianMingJiLuChuangJianCmd.setKouChuKeShi(dtoShangKeXueYuan.getKouChuKeShi());
            dianMingJiLuChuangJianCmd.setBeiZhu(dtoShangKeXueYuan.getBeiZhu());

            if(shangKeXueYuanLeiXing.equals(ShangKeXueYuanLeiXing.SHI_TING)) {
                // 试听学员
                // 点名记录 - 课消金额
                dianMingJiLuChuangJianCmd.setKeXiaoJinE(0.0);
                // 点名记录 - 课消金额 end
                //TODO 更新试听记录
            } else if(shangKeXueYuanLeiXing.equals(ShangKeXueYuanLeiXing.BU_KE)){
                // 补课学员
                // 学员课程_更新剩余课时&消课金额
                XueYuanKeCheng xueYuanKeCheng = xueYuanKeChengService.getXueYuanKeChengByXueYuanIdAndKeChengId(dtoShangKeXueYuan.getXueYuanId(), dtoPaiKeJiLu.getKeChengId());
                if(xueYuanKeCheng == null) {
                    throw new BusinessException("学员：" + dtoShangKeXueYuan.getXueYuanXingMing() + ", 未找到当前班级对应的课程");
                }
                // 本次课消金额
                Double keXiaoJinE = 0.0;
                keXiaoJinE = xueYuanKeCheng.dianMingGengXinShengYuKeShiXiaoKeJinE(dtoShangKeXueYuan.getKouChuKeShi());
                // 学员课程_更新剩余课时&消课金额 end

                // 点名记录 - 课消金额
                dianMingJiLuChuangJianCmd.setKeXiaoJinE(keXiaoJinE);
                // 点名记录 - 课消金额 end

                // 班级已授课时
                banJiYiShouKeShi += dtoShangKeXueYuan.getKouChuKeShi();
                // TODO 更新补课记录
            } else {
                // 本班学员或临时学员
                // 学员课程_更新剩余课时&消课金额
                XueYuanKeCheng xueYuanKeCheng = xueYuanKeChengService.getXueYuanKeChengByXueYuanIdAndKeChengId(dtoShangKeXueYuan.getXueYuanId(), dtoPaiKeJiLu.getKeChengId());
                if(xueYuanKeCheng == null) {
                    throw new BusinessException("学员：" + dtoShangKeXueYuan.getXueYuanXingMing() + ", 未找到当前班级对应的课程");
                }
                // 本次课消金额
                Double keXiaoJinE = 0.0;
                keXiaoJinE = xueYuanKeCheng.dianMingGengXinShengYuKeShiXiaoKeJinE(dtoShangKeXueYuan.getKouChuKeShi());
                // 学员课程_更新剩余课时&消课金额 end

                // 点名记录 - 课消金额
                dianMingJiLuChuangJianCmd.setKeXiaoJinE(keXiaoJinE);
                // 点名记录 - 课消金额 end

                // 班级已授课时
                banJiYiShouKeShi += dtoShangKeXueYuan.getKouChuKeShi();

                // 创建点名记录
                DianMingJiLu dianMingJiLu = dianMingJiLuService.chuangJian(dianMingJiLuChuangJianCmd);
                // 创建点名记录

                // 请假||未到 创建补课记录
                if(dtoShangKeXueYuan.getXueYuanDaoKeZhuangTai().equals(XueYuanDaoKeZhuangTai.QING_JIA) || dtoShangKeXueYuan.getXueYuanDaoKeZhuangTai().equals(XueYuanDaoKeZhuangTai.WEI_DAO) ) {
                    BuKeJiLu.ChuangJianCmd buKeJiLuChuangJianCmd = new BuKeJiLu.ChuangJianCmd();
                    buKeJiLuChuangJianCmd.setId(SnowflakeIdUtil.nextId());
                    buKeJiLuChuangJianCmd.setXueYuanId(dtoShangKeXueYuan.getXueYuanId());
                    buKeJiLuChuangJianCmd.setYuanPaiKeJiLuId(dtoPaiKeJiLu.getId());
                    buKeJiLuChuangJianCmd.setYuanDianMingJiLuId(dianMingJiLu.getId());
                    buKeJiLuService.chuangJian(buKeJiLuChuangJianCmd);
                }
                // 请假||未到 创建补课记录 end
            }
        }

        // 更新班级已授课时
        BanJi banJi = banJiService.getBanJiById(dtoPaiKeJiLu.getBanJiId());
        if(banJi == null) {
            throw new BusinessException("对应班级没有找到");
        }
        banJi.setYiShouKeShi(banJiYiShouKeShi);
        // 更新班级已授课时 end
    }

    /**
     * 获取课程实际单价
     * @param xueYuanKeCheng
     * @return
     */
    private Double getXueYuanKeChengDanJia(XueYuanKeCheng xueYuanKeCheng) {
        // 单价
        Double danJia = xueYuanKeCheng.getDanJia();
        // 课程数量
        Double keChengShuLiang = xueYuanKeCheng.getKeChengShuLiang();
        // 赠送课时
        Double zengSongKeShi = xueYuanKeCheng.getZengSongKeShi();
        // 优惠类型
        YouHuiLeiXing youHuiLeiXing = xueYuanKeCheng.getYouHuiLeiXing();
        // 优惠数量
        Double youHuiShuLiang = xueYuanKeCheng.getYouHuiShuLiang();
        // 实际单价
        Double shiJiDanJia = 0.0;
        if(YouHuiLeiXing.ZHI_JIAN.equals(youHuiLeiXing)) {
            shiJiDanJia = (danJia * keChengShuLiang - youHuiShuLiang) / keChengShuLiang;
        } else if (YouHuiLeiXing.ZHE_KOU.equals(youHuiLeiXing)){
            shiJiDanJia = ((danJia * keChengShuLiang) * (1 - youHuiShuLiang/100)) / keChengShuLiang;
        } else {
            throw new BusinessException("未知的优惠类型");
        }
        return shiJiDanJia;
    }
}
