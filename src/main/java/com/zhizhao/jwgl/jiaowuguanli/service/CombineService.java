package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;

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
}
