package com.zhizhao.jwgl.jiaowuguanli.domain.shitingjilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShiTingZhuangTai;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShiTingJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 排课记录Id
    @NotNull
    Long paiKeJiLuId;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 试听记录状态
    @NotNull
    @Enumerated(EnumType.STRING)
    ShiTingZhuangTai shiTingZhuangTai;

    // 点名记录id
    Long dianMingJiLuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShiTingJiLu that = (ShiTingJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1597102082;
    }
}
