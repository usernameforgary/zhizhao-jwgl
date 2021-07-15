package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXueYuan {
    Long id;
    // 所属账号Id
    Long zhangHaoId;
    // 所属账号手机
    String zhangHaoShouJi;
    // 姓名
    String xingMing;
    // 学员状态
    XueYuanZhuangTai xueYuanZhuangTai;
    // 头像
    String touXiang;
    // 性别
    XingBie xingBie;
    // 年龄
    Double nanLing;
    // 就读学校
    String jiuDuXueXiao;
    // 当前年级
    String danqQianNianJi;
    // 家庭住址
    String jiaTingZhuZhi;
    // 学员来源
    String xueYuanLaiYuan;
    // 备注信息
    String beiZhuXinXi;
    //跟进人
    Long genJinRenId;
    // 跟进人姓名
    String genJinRenXingMing;
}
