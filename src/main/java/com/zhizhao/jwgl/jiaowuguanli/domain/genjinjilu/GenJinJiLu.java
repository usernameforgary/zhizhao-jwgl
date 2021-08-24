package com.zhizhao.jwgl.jiaowuguanli.domain.genjinjilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 跟进记录
 */
@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class GenJinJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 内容
    String neiRong;

    // 跟进时间
    Long genJinShiJian;

    @NotNull
    // 跟进人Id
    Long genJinRenId;

    // 已完成
    @NotNull
    @Column(columnDefinition = "boolean default false")
    Boolean yiWanCheng = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenJinJiLu that = (GenJinJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 800270239;
    }
}
