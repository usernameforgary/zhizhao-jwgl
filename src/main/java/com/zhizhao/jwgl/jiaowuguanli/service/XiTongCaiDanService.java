package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;

import java.util.List;

public interface XiTongCaiDanService {
    List<XiTongCaiDan> huoQuXiTongCanDanByZhangHaoId(Long zhangHaoId);
}
