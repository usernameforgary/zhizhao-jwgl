package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXuYuanLiShi {
    Long id;
    // 姓名
    String xingMing;
    // 所属账号Id
    Long zhangHaoId;
    // 所属账号手机
    String shouJi;
    // 年龄
    Double nanLing;
    // 创建时间
    Long createTime;
    // 结业时间
    Long jieYeShiJian;
    // 最后就读课程
    DtoXueYuanKeCheng latestKeCheng;
    // 跟进人
    Long genJinRenId;
    // 跟进人姓名
    String genJinRenXingMing;
    // 学员状态
    XueYuanZhuangTai xueYuanZhuangTai;
}
