package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;

public interface BanJiMapper extends MyBaseMapper<BanJi> {
    IPage<DtoBanJi> huoQuBanJiLeiBiao(IPage page);
    DtoBanJi huoQuBanJiXiangQing(Long id);
}
