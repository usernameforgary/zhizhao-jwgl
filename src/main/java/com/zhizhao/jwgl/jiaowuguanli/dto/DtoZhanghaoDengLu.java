package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoZhanghaoDengLu {
    @NotBlank
    String shouJi;
    @NotBlank
    String miMa;
    @NotNull
    ZhangHaoLeiXing zhangHaoLeiXing;
}
