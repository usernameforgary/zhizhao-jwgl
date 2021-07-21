package com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.LiuShuiLieXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShouKuanFangShi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JiaoFeiLiShi {
    // 收款方式
    @NotNull
    @Enumerated(EnumType.STRING)
    ShouKuanFangShi shouKuanFangShi;
    // 缴费日期
    @NotNull
    Long jiaoFeiRiQi;
    // 缴费金额
    @NotNull
    Double jiaoFeiJinE;
    // 备注
    @NotNull
    String beiZhu;
    // 流水类型
    @Enumerated(EnumType.STRING)
    LiuShuiLieXing liuShuiLieXing;
}
