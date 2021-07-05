package com.zhizhao.jwgl.jiaowuguanli.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
public class AggRoot {
    @CreatedDate
    @NotNull
    Long createTime;
    @LastModifiedDate
    Long updateTime;
    @Version
    Integer version;
    @NotNull
    @Column(columnDefinition = "boolean default false")
    @Setter
    Boolean isDeleted = false;
}
