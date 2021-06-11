package com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class XuYuanGenJinJiLu {
    @AggregateIdentifier
    Long id;

    Long xuYuanId;
    Long shangCiGenJinJiLuId;
    Long genjinShiJian;
}
