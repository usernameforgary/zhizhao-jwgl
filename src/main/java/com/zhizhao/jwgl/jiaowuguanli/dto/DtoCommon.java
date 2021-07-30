package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用信息。如（标签、擅长科目、上课教室、班级分类等，只包含【名称】字段的实体）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCommon {
    private Long id;
    // 名称
    private String mingCheng;
}
