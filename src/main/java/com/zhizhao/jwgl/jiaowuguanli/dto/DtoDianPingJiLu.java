package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 点评记录Form表单提交的数据
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoDianPingJiLu {
    List<DtoChengZhangJiLu> chengZhangJiLuZu;
}
