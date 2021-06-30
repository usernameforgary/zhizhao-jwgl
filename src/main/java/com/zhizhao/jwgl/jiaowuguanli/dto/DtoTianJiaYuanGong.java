package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTianJiaYuanGong {
    // 姓名
    @NotBlank
    String xingMing;
    // 手机
    @NotBlank
    String shouJi;
    // 性别
    @NotNull
    XingBie xingBie;
    // 账号角色
    @NotEmpty
    Set<Long> jueSeZu;
    // 是否授课
    @NotNull
    Boolean isLaoShi;
    // 备注
    String beiZhu;
    // 擅长科目
    Set<Long> shanChangKeMuZu;
}
