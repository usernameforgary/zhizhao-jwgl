package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeGuiZe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBanJiPaiKeXinXi {
    Long id;
    // 班级Id
    Long banJiId;
    // 排课规则
    PaiKeGuiZe paiKeGuiZe;
    // 上课老师
    Long shangKeLaoShiId;
    // 上课老师姓名
    String shangKeLaoShiXingMing;
    // 上课教室
    Long shangKeJiaoShiId;
    // 上课教室名称
    String shangKeJiaoShiMingCheng;
    // 上课内容
    String shangKeNeiRong;
}
