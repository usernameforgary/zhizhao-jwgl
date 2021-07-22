package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoJiaoFeiJiLu;

/**
 * 缴费记录
 */
public interface JiaoFeiJiLuService {
    /**
     * 创建缴费记录
     * @param cmd
     * @return
     */
    Long chuangJian(JiaoFeiJiLu.ChuangJianCmd cmd);

    /**
     * 获取缴费记录列表
     * @param page
     * @param pageSize
     * @return
     */
    IPage<DtoJiaoFeiJiLu> huoQuJiaoFeiJiLuLieBiao(Integer page, Integer pageSize);
}
