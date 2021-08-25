package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GenJinZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXueYuanZaiDu {
    Long id;
    // 姓名
    String xingMing;
    // 所属账号Id
    Long zhangHaoId;
    // 所属账号手机
    String shouJi;
    // 年龄
    Double nanLing;

    // 最后一次跟进记录
    DtoGenJinJiLu latestGenJinJiLu;

    // 跟进人
    Long genJinRenId;
    // 跟进人姓名
    String genJinRenXingMing;
    // 学员状态
    XueYuanZhuangTai xueYuanZhuangTai;
}
