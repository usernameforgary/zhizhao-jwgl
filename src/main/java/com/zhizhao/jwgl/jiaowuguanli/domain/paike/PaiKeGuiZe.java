package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GuiZeChongFuFangShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GuiZeJiShuFangShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeFangShi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

/**
 * 排课规则
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaiKeGuiZe {
    // 排课方式
    @Enumerated(EnumType.STRING)
    PaiKeFangShi paiKeFangShi;

    // 规则排课【开始日期】
    Long guiZeKaiShiRiQi;

    // 规则排课【结束日期】
    Long guiZeJieShuRiQi;

    // 规则排课【重复方式】
    @Enumerated(EnumType.STRING)
    GuiZeChongFuFangShi guiZeChongFuFangShi;

    // 规则排课【结束方式】
    @Enumerated(EnumType.STRING)
    GuiZeJiShuFangShi guiZeJiShuFangShi;

    // 规则排课【排课次数】
    Long guiZePaiKeCiShu;

    // 日历排课【上课日期】组
    @Type(type = "json")
    @Column(columnDefinition = "json")
    Set<Long> riLiShangKeRiQi;

    // 排课上课时间组;【日历排课】，或者【规则排课】且【每天重复】时，只有一组数据
    @Type(type = "json")
    @Column(columnDefinition = "json")
    Set<PaiKeShangKeShiJian> paiKeShangKeShiJianZu;
}
