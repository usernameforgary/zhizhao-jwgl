package com.zhizhao.jwgl.jiaowuguanli.domain.biaoqian;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BiaoQian {
   @AggregateIdentifier
   private Long id;
   private String name;
}
