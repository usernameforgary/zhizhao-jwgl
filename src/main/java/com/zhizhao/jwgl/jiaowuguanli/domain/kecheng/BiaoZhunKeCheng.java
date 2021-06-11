package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BiaoZhunKeCheng {
   @AggregateIdentifier
   private Long id;
}
