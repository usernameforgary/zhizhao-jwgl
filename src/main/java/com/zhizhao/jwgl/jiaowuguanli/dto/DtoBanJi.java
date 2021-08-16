package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJiXueYuan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBanJi {
    Long id;
    //名称
    String mingCheng;
    // 课程名称
    String keChengMingCheng;
    // 课程Id
    Long keChengId;
    // 班级老师Id
    Long banJiLaoShiId;
    // 班级老师
    String banJiLaoShiXingMing;
    // 班级人数
    Integer renShu;
    // 班级学员
    Set<BanJiXueYuan> banJiXueYuanZu;
    // 容量
    Integer rongLiang;
    // 已排课次
    Integer paiKeCiShu;
    // 已上课次
    Integer yiShangKeCiShu;
    // 已授课时
    Double yiShouKeShi;
    // 班级分类
    String banJiFenLeiMingCheng;
    // 默认授课课时
    Double moRenShouKeKeShi;
    // 上课教室Id
    Long shangKeJiaoShiId;
    // 上课教室
    String shangKeJiaoShi;
    // 备注
    String beiZhu;
    // 请假扣课时
    boolean qingJiaKouKeShi;
    // 未到扣课时
    boolean weiDaoKouKeShi;
}
