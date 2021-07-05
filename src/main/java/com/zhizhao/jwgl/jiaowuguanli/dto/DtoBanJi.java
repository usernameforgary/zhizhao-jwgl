package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJiXuYuan;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.DingJiaBiaoZhun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;
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
    // 班级老师
    String banJiLaoShiXingMing;
    // 班级人数
    Integer renShu;
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
}