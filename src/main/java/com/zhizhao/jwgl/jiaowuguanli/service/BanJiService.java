package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;

public interface BanJiService {
    IPage<DtoBanJi> huoQuBanJiLieBiao(Integer pageNum, Integer pageSize);
    DtoBanJi huoQuBanJiXiangQing(Long id);
}
