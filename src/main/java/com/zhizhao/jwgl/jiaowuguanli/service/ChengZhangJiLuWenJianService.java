package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjiluwenjian.ChengZhangJiLuWenJian;

public interface ChengZhangJiLuWenJianService {
    /**
     * 创建成长记录
     * @param chuangJianCmd
     * @return
     */
    ChengZhangJiLuWenJian chuangJian(ChengZhangJiLuWenJian.ChuangJianCmd chuangJianCmd);
}
