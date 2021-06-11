package com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class XueYuan {
   @AggregateIdentifier
   private Long id;
   private String touXiang;
}
