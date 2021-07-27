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
    Long id;
    // 所属学员ID, (前端创建学员课程，学员未创建之前为空)
    Long xueYuanId;
    // 所属学员姓名
    String xueYuanXingMing;

    @NotNull
    //课程ID
    Long keChengId;

    //课程名称  (简单查询，如mybatis直接join返回【课程名称】）
    String keChengMingCheng;
    //课程信息 (复杂查询时，或前端form表单提交，用到)
    DtoKeCheng keCheng;

    // 班级名称 (简单查询，如mybatis直接join返回【班级姓名】）
    String banJiMingCheng;
    // 班级老师 (简单查询，如mybatis直接join返回【班级老师】）
    String banJiLaoShiXingMing;
    // 班级信息 (复杂查询时，或前端form表单提交，用到)
    DtoBanJi banJi;

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

    // 剩余课时
    Double shengYuKeShi;

    // 备注
    String beiZhu;
}
