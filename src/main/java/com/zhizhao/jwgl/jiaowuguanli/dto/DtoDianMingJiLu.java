package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

// 点名记录Dto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoDianMingJiLu {
    @NotNull
    Long id;
    // 排课记录Id
    @NotNull
    Long paiKeJiLuId;
    // 排课记录状态
    PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;
    // 学员Id
    @NotNull
    Long xueYuanId;
    // 上课学员类型
    @NotNull
    ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;
    // 学员到课状态
    @NotNull
    XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai;
    // 扣除课时
    @NotNull
    Double kouChuKeShi;
    // 课消金额 (赠送课时，课消金额为0)
    @NotNull
    Double keXiaoJinE;
    // 备注
    String beiZhu;

    // 点名时间
    Long dianMingShiJian;
    // 班级名称
    String banJiMingCheng;
    // 班级Id
    Long banJiId;
    // 上课日期
    Long shangKeRiQi;
    // 上课开始时间
    Long shangKeShiJianStart;
    // 上课结束时间
    Long shangKeShiJianEnd;
    // 上课老师Id
    Long shangKeLaoShiId;
    // 上课老师姓名
    String shangKeLaoShiXingMing;
    // 学员姓名
    String xueXueYuanXingMing;
    // 联系电话
    String shouJi;
    // 课程名称
    String keChengMingCheng;
    // 点评内容
    String dianPingNeiRong;
    // 成长记录Id（即点评记录ID）
    Long chengZhangJiLuId;
    // 上课内容
    String shangKeNeiRong;
}
