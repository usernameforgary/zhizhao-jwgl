package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GenJinZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YiXiangJiBie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// 潜在学员Dto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXueYuanQianZai {
    Long id;
    // 姓名
    String xingMing;
    // 所属账号Id
    Long zhangHaoId;
    // 所属账号手机
    String shouJi;
    // 跟进状态
    GenJinZhuangTai genJinZhuangTai;
    // 意向级别
    YiXiangJiBie yiXiangJiBie;
    //跟进人
    Long genJinRenId;
    // 跟进人姓名
    String genJinRenXingMing;
    // 最后一次跟进记录
    DtoGenJinJiLu latestGenJinJiLu;
    // 下次跟进记录
    DtoGenJinJiLu nextGenJinJiLu;
    // 年龄
    Double nanLing;
    // 标签组
    Set<DtoCommon> xueYuanBiaoQianZu;
    // 创建时间
    Long createTime;
    // 学员状态
    XueYuanZhuangTai xueYuanZhuangTai;
}
