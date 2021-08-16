package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoDianMingJiLuQuery {
    Integer pageNum;
    Integer pageSize;
    // 上课日期 - 开始
    Long shangKeRiQiBegin;
    // 上课日期 - 结束
    Long shangKeRiQiEnd;
    // 班级Id
    Long banJiId;
    // 上课老师Id
    Long shangKeLaoShiId;
    // 学员Id
    Long xueYuanId;
}
