package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排课记录，上课学员Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoShangKeXueYuan {
    // 排课记录Id
    Long paiKeJiLuId;
    // 学员id
    Long xueYuanId;
    // 学员姓名
    String xueYuanXingMing;
    // 删除
    Boolean isDeleted;
    // 上课学员类型
    ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;
    // 学员到课状态
    XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai;
    // 补课学员的补课记录Id，上课学员类型为补课学员时需要提供补课记录Id
    Long buKeJiLuId;
    // 试听学员的试听记录Id, 上课学员类型为试听学员时需要提供试听记录Id
    Long shiTingJiLuId;

    // 扣除课时
    Double kouChuKeShi;
    // 课消金额
    Double keXiaoJinE;
    // 备注
    String beiZhu;
    // 剩余课时
    Double shengYuKeShi;
    // 手机号
    String shouJi;
}
