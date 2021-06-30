package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;

import java.util.List;

public interface XiTongCaiDanMapper extends MyBaseMapper<XiTongCaiDan> {
    List<XiTongCaiDan> huoQuXiTongCaiDanByZhangHaoId(Long zhangHaoId);
}
