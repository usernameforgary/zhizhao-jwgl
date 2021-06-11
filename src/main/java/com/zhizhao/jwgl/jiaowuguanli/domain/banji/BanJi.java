package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Set;

@Aggregate
public class BanJi {
    @AggregateIdentifier
    private Long Id;
    private Long biaoZhunKeChengId;
    private Set<Long> xueYuanLieBiao;
    private int rongLiang;
    private int zaiDu;
    private Long yiShouKeShi;
}
