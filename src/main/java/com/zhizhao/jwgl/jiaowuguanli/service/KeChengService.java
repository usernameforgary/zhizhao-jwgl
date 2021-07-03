package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;

public interface KeChengService {
    IPage<DtoKeCheng> huoQuKeChengLieBiao(Integer pageNum, Integer pageSize);
}
