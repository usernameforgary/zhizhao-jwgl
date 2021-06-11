package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;
import java.util.Set;

@Aggregate
public class BanJiPaiKeXinXi {
    @AggregateIdentifier
    private Long id;
    private Long banJiId;
    private Map<String, Object> paiKeGuiZe;
    private Set<Long> paiKeJiLu;
}
