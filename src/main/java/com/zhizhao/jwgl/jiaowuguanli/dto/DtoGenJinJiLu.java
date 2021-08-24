package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

// 跟进记录Dto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoGenJinJiLu {
    @NotNull
    Long id;
    // 学员Id
    @NotNull
    Long xueYuanId;
    // 内容
    String neiRong;
    // 跟进时间
    Long genJinShiJian;
    @NotNull
    // 跟进人Id
    Long genJinRenId;
    // 已完成
    @NotNull
    Boolean yiWanCheng = false;
}
