package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoChengZhangJiLuWenJian {
    @NotNull
    Long id;
    // 名称
    String mingCheng;
    // 后缀
    @NotNull
    String houZhui;
    // oss上key
    @NotNull
    String ossKey;
    // oss的bucket名称
    String ossBucketName;
    // 大小
    @NotNull
    Integer daXiao;
    // 文件Url
    String url;
}
