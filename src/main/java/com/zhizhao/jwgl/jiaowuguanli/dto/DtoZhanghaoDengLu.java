package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoZhanghaoDengLu {
    @NotBlank
    String shouJi;
    @NotBlank
    String miMa;
}
