package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaiKeJiLu {
    @AggregateIdentifier
    private Long id;
    private Long banJiPaiKeXinXiId;
}
