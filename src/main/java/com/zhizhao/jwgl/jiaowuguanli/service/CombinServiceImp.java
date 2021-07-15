package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CombinServiceImp implements CombineService {
    @Autowired
    BanJiPaiKeXinXiService banJiPaiKeXinXiService;

    @Autowired
    ZhangHaoService zhangHaoService;

    @Autowired
    XueYuanService xueYuanService;

    /**
     * 创建班级排课信息
     * @param cmd
     */
    @Transactional
    @Override
    public void chuangJianBanJiPaiKeXinXi(BanJiPaiKeXinXi.ChuangJianCommand cmd) {
        // 创建班级排课信息
        Long id = banJiPaiKeXinXiService.chuangJianBanJiPaiKeXinXi(cmd);
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
        ZhangHao zhangHao = zhangHaoService.getZhangHaoByShouJiAndLeiXing(Long.parseLong(dtoXueYuan.getZhangHaoShouJi()), ZhangHaoLeiXing.XUE_YUAN);
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
