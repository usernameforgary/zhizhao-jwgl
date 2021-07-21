package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;

public interface JiaoFeiJiLuService {
    /**
     * 创建缴费记录
     * @param cmd
     * @return
     */
    Long chuangJian(JiaoFeiJiLu.ChuangJianCmd cmd);
}
