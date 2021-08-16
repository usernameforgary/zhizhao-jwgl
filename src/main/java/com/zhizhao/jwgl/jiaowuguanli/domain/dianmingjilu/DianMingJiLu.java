package com.zhizhao.jwgl.jiaowuguanli.domain.dianmingjilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 点名记录
 */
@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DianMingJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 排课记录Id
    Long paiKeJiLuId;

    // 学员Id
    Long xueYuanId;

    // 上课学员类型
    @NotNull
    @Enumerated(EnumType.STRING)
    ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;

    // 学员到课状态
    @Enumerated(EnumType.STRING)
    XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai;

    // 扣除课时
    @NotNull
    @Column(columnDefinition = "double default 0")
    Double kouChuKeShi;

    // 课消金额 (赠送课时，课消金额为0)
    Double keXiaoJinE;

    // 备注
    String beiZhu;

    /**
     * 创建点名记录
     * @param cmd
     * @return
     */
    public static DianMingJiLu chuangJian(ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            cmd.setId(SnowflakeIdUtil.nextId());
        }
        if(cmd.getPaiKeJiLuId() == null) {
            throw new BusinessException("请指定要点名的排课记录");
        }
        if(cmd.getXueYuanId() == null) {
            throw new BusinessException("请指定要点名的学员");
        }
        if(cmd.getShangKeXueYuanLeiXing() == null) {
            throw new BusinessException("请指定点名的学员的类型");
        }
        if(cmd.getXueYuanDaoKeZhuangTai() == null) {
            throw new BusinessException("请指定点名的学员的到课状态");
        }
        if(cmd.getKouChuKeShi() == null) {
            throw new BusinessException("请指定点名的学员的课消数量");
        }
        if(cmd.getKeXiaoJinE() == null) {
            throw new BusinessException("请指定点名的学员的课消金额");
        }

        DianMingJiLu dianMingJiLu = DianMingJiLu.builder()
                .id(cmd.id)
                .paiKeJiLuId(cmd.paiKeJiLuId)
                .xueYuanId(cmd.xueYuanId)
                .shangKeXueYuanLeiXing(cmd.shangKeXueYuanLeiXing)
                .xueYuanDaoKeZhuangTai(cmd.xueYuanDaoKeZhuangTai)
                .kouChuKeShi(cmd.kouChuKeShi)
                .keXiaoJinE(cmd.keXiaoJinE)
                .beiZhu(cmd.beiZhu)
                .build();
        return dianMingJiLu;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        // 排课记录Id
        Long paiKeJiLuId;
        @NotNull
        // 学员Id
        Long xueYuanId;
        // 上课学员类型
        @NotNull
        ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;
        // 学员到课状态
        @NotNull
        XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai;
        // 扣除课时
        @NotNull
        Double kouChuKeShi;
        // 课消金额 (赠送课时，课消金额为0)
        @NotNull
        Double keXiaoJinE;
        // 备注
        String beiZhu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DianMingJiLu that = (DianMingJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1749801226;
    }
}
