package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjilu.ChengZhangJiLu;

public interface ChengZhangJiLuService {
    /**
     * 创建成长记录
     * @param cmd
     * @return
     */
    ChengZhangJiLu chuangJian(ChengZhangJiLu.ChuangJianCmd cmd);
}
