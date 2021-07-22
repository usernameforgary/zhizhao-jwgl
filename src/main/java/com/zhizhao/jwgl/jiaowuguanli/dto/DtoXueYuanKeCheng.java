package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YouHuiLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.DingJiaBiaoZhun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 学员课程DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXueYuanKeCheng {
    // 所属学员ID，学员未创建之前为空
    Long xueYuanId;

    @NotNull
    //课程ID
    Long keChengId;

    //课程名称
    String keChengMingCheng;

    //课程信息
    DtoKeCheng keCheng;

    //定价标准
    DingJiaBiaoZhun dingJiaBiaoZhun;

    //课程状态
    @NotNull
    XueYuanKeChengZhuangTai keChengZhuangTai;

    //课程类型
    @NotNull
    KeChengLeiXing keChengLeiXing;

    //单价
    @NotNull
    Double danJia;

    //课程数量
    @NotNull
    Double keChengShuLiang;

    //赠送课时
    Double zengSongKeShi;

    //优惠类型
    YouHuiLeiXing youHuiLeiXing;

    //优惠数量
    Double youHuiShuLiang;

    // 备注
    String beiZhu;
}
