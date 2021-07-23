package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoPageResult<T> {
    long current;
    long pages;
    List<T> records;
    long size;
    long total;
}