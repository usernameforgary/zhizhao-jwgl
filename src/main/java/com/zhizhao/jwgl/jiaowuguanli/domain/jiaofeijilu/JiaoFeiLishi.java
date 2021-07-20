package com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.LiuShuiLieXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShouKuanFangShi;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class JiaoFeiLishi {
    // 收款方式
    @NotNull
    ShouKuanFangShi shouKuanFangShi;
    // 缴费日期
    @NotNull
    Long jiaoFeiRiQi;
    // 备注
    @NotNull
    String beiZhu;
    // 流水类型
    LiuShuiLieXing liuShuiLieXing;
}
