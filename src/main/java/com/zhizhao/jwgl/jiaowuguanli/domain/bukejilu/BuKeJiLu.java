package com.zhizhao.jwgl.jiaowuguanli.domain.bukejilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.BuKeZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

// 补课记录
@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuKeJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 原来的排课记录id
    @NotNull
    Long yuanPaiKeJiLuId;

    // 原来的点名记录Id
    @NotNull
    Long yuanDianMingJiLuId;

    // 补课状态
    @NotNull
    @Enumerated(EnumType.STRING)
    BuKeZhuangTai buKeZhuangTai;

    // 新的排课记录Id
    Long xinPaiKeJiLuId;

    // 新的点名记录Id
    Long xinDianMingJiLuId;

    public static BuKeJiLu chuangJian(ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            throw new BusinessException("请指定补课记录Id");
        }
        if(cmd.getXueYuanId() == null) {
            throw new BusinessException("请指定补课学员");
        }
        if(cmd.getYuanDianMingJiLuId() == null) {
            throw new BusinessException("请指定原点名记录");
        }
        if(cmd.getYuanPaiKeJiLuId() == null) {
            throw new BusinessException("请指定原上课记录");
        }

        BuKeJiLu buKeJiLu = BuKeJiLu.builder()
                .id(cmd.id)
                .buKeZhuangTai(BuKeZhuangTai.DAI_BU_KE)
                .xueYuanId(cmd.getXueYuanId())
                .yuanPaiKeJiLuId(cmd.yuanPaiKeJiLuId)
                .yuanDianMingJiLuId(cmd.yuanDianMingJiLuId)
                .build();

        return buKeJiLu;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;

        // 学员Id
        @NotNull
        Long xueYuanId;

        // 原来的排课记录id
        @NotNull
        Long yuanPaiKeJiLuId;

        // 原来的点名记录Id
        @NotNull
        Long yuanDianMingJiLuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BuKeJiLu buKeJiLu = (BuKeJiLu) o;

        return Objects.equals(id, buKeJiLu.id);
    }

    @Override
    public int hashCode() {
        return 386421040;
    }
}
