package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排课记录查询参数Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoPaiKeJiLuQuery {
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
    // 点名状态
    PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;
}
