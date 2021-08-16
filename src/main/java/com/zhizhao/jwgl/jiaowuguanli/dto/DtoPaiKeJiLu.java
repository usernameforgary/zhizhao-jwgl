package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeGuiZe;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.ShangKeXueYuan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

/**
 * 排课记录Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoPaiKeJiLu {
    Long id;
    // 班级排课信息Id
    Long banJiPaiKeXinXiId;
    // 排课规则
    PaiKeGuiZe paiKeGuiZe;
    // 上课日期
    Long shangKeRiQi;
    // 上课老师
    Long shangKeLaoShiId;
    // 上课老师姓名
    String shangKeLaoShiXingMing;
    // 上课教室
    Long shangKeJiaoShiId;
    // 上课教室名称
    String shangKeJiaoShiMingCheng;
    // 上课开始时间
    Long shangKeShiJianStart;
    // 上课结束时间
    Long shangKeShiJianEnd;
    // 授课课时
    Double shouKeKeShi;
    // 上课内容
    String shangKeNeiRong;
    // 排课记录状态
    @Enumerated(EnumType.STRING)
    PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;
    // 上课学员
    Set<DtoShangKeXueYuan> shangKeXueYuanZu;
    // 点名时间
    Long dianMingShiJian;
    // 班级所属课程id
    Long keChengId;
    // 班级Id
    Long banJiId;
}
