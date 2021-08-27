package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoLaoShi {
    Long id;
    // 姓名
    String xingMing;
    // 所属账号Id
    Long zhangHaoId;
    // 性别
    XingBie xingBie;
    // 擅长科目组
    Set<DtoCommon> shanChangKeMuZu;
    // 手机
    String shouJi;
    // 上月点名率
    Double shangYueDianMingLv;
    // 上月课时
    Double shangYueKeShi;
    // 本月课时
    Double benYueKeShi;
    // 已上课时
    Double yiShangKeShi;
    // 在职状态
    Boolean zaiZhiZhuangTai;
}
