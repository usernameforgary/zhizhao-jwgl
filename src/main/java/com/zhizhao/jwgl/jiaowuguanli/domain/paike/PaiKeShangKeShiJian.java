package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeShangKeTian;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 排课上课时间，周几上课，上课[开始|结束]时间
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaiKeShangKeShiJian {
    // 哪天(周几)上课，排课方式为【日历排课】时，或者排课方式为【规则排课】且【重复方式】为【每天重复】时，值为daily
    @Enumerated(EnumType.STRING)
    PaiKeShangKeTian paiKeShangKeTian;
    // 上课开始时间
    Long startTime;
    // 上课结束时间
    Long stopTime;
}
