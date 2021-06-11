package com.zhizhao.jwgl.jiaowuguanli.domain.juese;

import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
@Data
public class JueSe {
    @AggregateIdentifier
    private String id;
    private String jueSeName;
    private List<String> jueSeCanDanZu;
}
