package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ChengZhangJiLuLeiXing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

// 成长记录Dto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoChengZhangJiLu {
    @NotNull
    Long id;
    // 学员Id
    @NotNull
    Long xueYuanId;
    // 内容
    @NotNull
    String neiRong;

    // 成长记录文件组
    Set<DtoChengZhangJiLuWenJian> chengZhangJiLuWenJianZu;

    // 排课记录Id，成长记录类型 = [KE_HOU_DIAN_PING： 课后点评]
    Long paiKeJiLuId;
    // 成长记录类型
    @NotNull
    ChengZhangJiLuLeiXing chengZhangJiLuLeiXing;
    // 家长已读
    Boolean jiaZhangYiDu;
}
