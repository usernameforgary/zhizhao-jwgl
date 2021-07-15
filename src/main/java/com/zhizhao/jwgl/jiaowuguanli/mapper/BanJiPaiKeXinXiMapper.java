package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiPaiKeXinXi;

import java.util.List;

public interface BanJiPaiKeXinXiMapper extends MyBaseMapper<BanJiPaiKeXinXi> {
    List<DtoBanJiPaiKeXinXi> huoQuBanJiPaiKeXinXi(Long banJiId);
}
