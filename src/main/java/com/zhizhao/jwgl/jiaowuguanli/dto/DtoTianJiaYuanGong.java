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
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTianJiaYuanGong {
    Long id;
    //姓名
    @NotBlank
    String xingMing;
    //手机
    @NotBlank
    String shouJi;
    //性别
    XingBie xingBie;
    //密码
    String miMa;
    //账号角色
    Set<Long> jueSeZu;
    //账号类型
    ZhangHaoLeiXing zhangHaoLeiXing = ZhangHaoLeiXing.YUAN_GONG;
    //是否授课
    Boolean isLaoShi;
    //备注
    String beiZhu;
    //在职状态
    Boolean zaiZhiZhuangTai = true;
}
