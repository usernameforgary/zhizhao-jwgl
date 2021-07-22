package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoJiaoFeiJiLu;

public interface JiaoFeiJiLuMapper extends MyBaseMapper<JiaoFeiJiLu> {
    /**
     * 获取缴费记录列表
     * @param page
     * @return
     */
    IPage<DtoJiaoFeiJiLu> huoQuJiaoFeiJiLu(IPage page);
}
