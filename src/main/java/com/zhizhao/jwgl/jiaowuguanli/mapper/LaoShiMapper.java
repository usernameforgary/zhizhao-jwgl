package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;

import java.util.List;

public interface LaoShiMapper extends MyBaseMapper<LaoShi> {
    List<DtoLaoShi> huoQuLaoShiAll();
}
