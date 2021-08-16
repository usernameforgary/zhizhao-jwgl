package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.bukejilu.BuKeJiLu;

public interface BuKeJiLuService {
    /**
     * 创建补课记录
     * @param cmd 补课记录创建cmd
     * @return
     */
    BuKeJiLu chuangJian(BuKeJiLu.ChuangJianCmd cmd);
}
