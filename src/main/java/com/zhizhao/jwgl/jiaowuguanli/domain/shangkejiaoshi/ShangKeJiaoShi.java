package com.zhizhao.jwgl.jiaowuguanli.domain.shangkejiaoshi;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 上课教室
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ShangKeJiaoShi extends AggRoot {
    @Id
    @NotNull
    Long id;

    @Column(nullable = false, unique = true)
    String mingCheng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShangKeJiaoShi that = (ShangKeJiaoShi) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 42209684;
    }
}
