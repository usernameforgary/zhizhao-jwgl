package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanBaoMing;

public interface CombineService {
    /**
     * 创建班级排课信息
     * @param cmd
     */
    void chuangJianBanJiPaiKeXinXi(BanJiPaiKeXinXi.ChuangJianCommand cmd);

    /**
     * 创建学员
     * @param dtoXueYuan
     * @return
     */
    Long chuangJianXueYuan(DtoXueYuan dtoXueYuan);

    /**
     * 学员报名
     * @param dtoXueYuanBaoMing 学员报名dto
     */
    void xueYuanBaoMing(DtoXueYuanBaoMing dtoXueYuanBaoMing);

    /**
     * 学员课程选择班级
     * @param xueYuanId 学员Id
     * @param xueYuanKeChengId 学员课程Id
     * @param selectedBanJiId 当前选择班级Id
     * @param previousBanJiId 原来选择班级Id
     */
    void xueYuanXuanBan(Long xueYuanId, Long xueYuanKeChengId, Long selectedBanJiId, Long previousBanJiId);
}
