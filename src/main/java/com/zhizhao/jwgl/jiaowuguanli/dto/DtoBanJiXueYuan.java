package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 班级学员Dto
 * 包含学员的学员课程
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBanJiXueYuan {
    // 学员id
    @NotNull
    Long xueYuanId;
    // 班级Id
    @NotNull
    Long banJiId;
    // 学员名称
    String xueYuanXingMing;
    // 学员性别
    XingBie xingBie;
    //手机号
    String shouJi;
    Boolean isDeleted;
    DtoXueYuanKeCheng xueYuanKeCheng;
}
