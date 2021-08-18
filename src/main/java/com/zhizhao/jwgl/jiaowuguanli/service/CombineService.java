package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;

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
     * @param targetBanJiId 目标班级Id
     */
    void xueYuanXuanBan(Long xueYuanId, Long xueYuanKeChengId, Long targetBanJiId);

    /**
     * 缴费记录确认
     * @param jiaoFeiJiLuId 缴费记录Id
     * @param jiaoFeiJiLuZhuangTai 缴费记录状态
     */
    void jiaoFeiJiLuQueRen(Long jiaoFeiJiLuId, JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai);

    /**
     * 排课记录点名
     * @param dtoPaiKeJiLu 排课记录点名信息
     */
    void paiKeJiLuDianMing(DtoPaiKeJiLu dtoPaiKeJiLu);

    /**
     * 导出排课记录
     * @param dtoPaiKeJiLuQuery
     */
    void daoChuPaiKeJiLu(DtoPaiKeJiLuQuery dtoPaiKeJiLuQuery);

    /**
     * 导出学员点名记录
     * @param dtoDianMingJiLuQuery
     * @return
     */
    void daoChuXueYuanDianMingJiLu(DtoDianMingJiLuQuery dtoDianMingJiLuQuery);
}
