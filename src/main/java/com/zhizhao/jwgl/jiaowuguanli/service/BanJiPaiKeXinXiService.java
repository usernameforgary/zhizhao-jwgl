package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiPaiKeXinXi;

import java.util.List;

public interface BanJiPaiKeXinXiService {
    Long chuangJianBanJiPaiKeXinXi(BanJiPaiKeXinXi.ChuangJianCommand cmd);
    List<DtoBanJiPaiKeXinXi> huoQuBanJiPaiKeXinXi(Long banJiId);
}
