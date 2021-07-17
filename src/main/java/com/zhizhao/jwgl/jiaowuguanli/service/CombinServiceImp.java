package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJiXuYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.*;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.*;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
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
        for(BanJiXuYuan banJiXuYuan : banJi.getBanJiXueYuanZu()) {
            ShangKeXueYuan shangKeXueYuan = new ShangKeXueYuan();
            shangKeXueYuan.setXueYuanId(banJiXuYuan.getXueYuanId());
            shangKeXueYuan.setShangKeXueYuanLeiXing(ShangKeXueYuanLeiXing.BEN_BAN);
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
}
