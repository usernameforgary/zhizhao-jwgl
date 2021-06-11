package com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;
import java.util.Set;

@Aggregate
public class ZhangHao {
    @AggregateIdentifier
    Long id;
    String shouJi;
    String miMa;
    String xingMing;
    Set<String> juSeStr;
    Map<String, Object> fangWenZiYuanJson;
}
