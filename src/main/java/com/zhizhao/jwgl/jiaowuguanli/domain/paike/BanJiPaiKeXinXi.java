package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GuiZeChongFuFangShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GuiZeJiShuFangShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeFangShi;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.MyDateUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

/**
 * 班级排课信息
 */
@Entity
@Builder(toBuilder = true)
@TypeDef(name = "json", typeClass = JsonType.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BanJiPaiKeXinXi extends AggRoot {
    @Column(nullable = false)
    @Id
    @NotNull
    Long id;
    // 班级Id
    @NotNull
    Long banJiId;

    // 排课规则
    @Type(type = "json")
    @Column(columnDefinition = "json")
    PaiKeGuiZe paiKeGuiZe;

    // 上课老师
    Long shangKeLaoShiId;
    // 上课教室
    Long shangKeJiaoShiId;
    // 上课内容
    String shangKeNeiRong;

    /**
     * 创建班级排课信息
     * @param cmd
     * @return
     */
    public static BanJiPaiKeXinXi chuangJian(ChuangJianCommand cmd) {
        if(cmd.getBanJiId() == null) {
            throw new BusinessException("请指定 排课班级");
        }
        if(cmd.getPaiKeGuiZe() == null) {
            throw new BusinessException("请指定 上课规则");
        }
        PaiKeGuiZe paiKeGuiZe = cmd.getPaiKeGuiZe();
        // 排课方式：GUI_ZE_PAI_KE（规则排课）|| RI_LI_PAI_KE（日历排课）
        PaiKeFangShi paiKeFangShi = paiKeGuiZe.getPaiKeFangShi();
        // 校验上课时间 start
        validateShangKeShiJian(paiKeGuiZe.getPaiKeShangKeShiJianZu());
        // 校验上课时间 stop
        if(paiKeFangShi.equals(PaiKeFangShi.GUI_ZE_PAI_KE)) {
            // 重复方式：MEI_TIAN（每天重复）|| MEI_ZHOU（每周重复）, 校验 start
            GuiZeChongFuFangShi guiZeChongFuFangShi = paiKeGuiZe.getGuiZeChongFuFangShi();
            if(guiZeChongFuFangShi == null) {
                throw new BusinessException("请指定 重复方式");
            }
            if(guiZeChongFuFangShi != GuiZeChongFuFangShi.MEI_TIAN && guiZeChongFuFangShi != GuiZeChongFuFangShi.MEI_ZHOU) {
                throw new BusinessException("未知的 重复方式");
            }
            // 重复方式：MEI_TIAN（每天重复）|| MEI_ZHOU（每周重复）, 校验 end

            // 结束方式：RI_QI_JIE_SHU（日期结束）|| CI_SHU_JIE_SHU（次数结束）, 校验 start
            GuiZeJiShuFangShi guiZeJiShuFangShi = paiKeGuiZe.getGuiZeJiShuFangShi();
            if(guiZeJiShuFangShi == null) {
                throw new BusinessException("请指定 结束方式");
            }
            if(guiZeJiShuFangShi.equals(GuiZeJiShuFangShi.CI_SHU_JIE_SHU)) {
                Long paiKeCiShu = paiKeGuiZe.getGuiZePaiKeCiShu();
                if(paiKeCiShu == null) {
                    throw new BusinessException("请指定 排课次数");
                }
            } else if(guiZeJiShuFangShi.equals(GuiZeJiShuFangShi.RI_QI_JIE_SHU)) {
                // 开始日期，需晚于结束时期判断
                Long kaiShiRiQiMillisecond = paiKeGuiZe.getGuiZeKaiShiRiQi();
                Long jieShuRiQiMillisecond = paiKeGuiZe.getGuiZeJieShuRiQi();
                if(!MyDateUtil.isTimeBefore(kaiShiRiQiMillisecond, jieShuRiQiMillisecond, true)) {
                    throw new BusinessException("排课 结束日期 需晚于 开始日期");
                }
                // 开始日期，需晚于结束时期判断 end
            } else {
                throw new BusinessException("未知的 结束方式");
            }
            // 结束方式：RI_QI_JIE_SHU（日期结束）|| CI_SHU_JIE_SHU（次数结束）, 校验 start
        } else if(paiKeFangShi.equals(PaiKeFangShi.RI_LI_PAI_KE)) {
            Set<Long> riLiShangKeRiQi = paiKeGuiZe.getRiLiShangKeRiQi();
            if (riLiShangKeRiQi == null && riLiShangKeRiQi.size() == 0) {
                throw new BusinessException("请选择排课日期");
            }
        }

        BanJiPaiKeXinXi banJiPaiKeXinXi = BanJiPaiKeXinXi.builder()
                .id(cmd.id)
                .banJiId(cmd.banJiId)
                .paiKeGuiZe(cmd.paiKeGuiZe)
                .shangKeLaoShiId(cmd.shangKeLaoShiId)
                .shangKeJiaoShiId(cmd.shangKeJiaoShiId)
                .shangKeNeiRong(cmd.shangKeNeiRong)
                .build();
        return banJiPaiKeXinXi;
    }

    /**
     * 上课时间校验（上课时间非空，开始时间&&结束时间非空，开始时间晚于结束时间）
     * @param paiKeShangKeShiJianSet 上课时间组
     */
    private static void validateShangKeShiJian(Set<PaiKeShangKeShiJian> paiKeShangKeShiJianSet) {
        if(paiKeShangKeShiJianSet == null || paiKeShangKeShiJianSet.isEmpty()) {
            throw new BusinessException("上课时间 不能为空");
        }
        for(PaiKeShangKeShiJian paiKeShangKeShiJian: paiKeShangKeShiJianSet) {
            Long startMillisecond = paiKeShangKeShiJian.getStartTime();
            Long stopMillisecond = paiKeShangKeShiJian.getStopTime();
            if(startMillisecond == null || stopMillisecond == null) {
                throw new BusinessException("请正确指定 上课时间");
            }
            if(!MyDateUtil.isTimeBefore(startMillisecond, stopMillisecond, false)) {
                throw new BusinessException("上课 开始时间 应晚于上课 结束时间 ");
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCommand {
        @NotNull
        Long id;
        @NotNull
        Long banJiId;
        @NotNull
        PaiKeGuiZe paiKeGuiZe;
        @NotNull
        Long shangKeLaoShiId;
        @NotNull
        Long shangKeJiaoShiId;
        @NotNull
        @Size(min = 1, max = 50)
        String shangKeNeiRong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJiPaiKeXinXi that = (BanJiPaiKeXinXi) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1712168591;
    }
}
