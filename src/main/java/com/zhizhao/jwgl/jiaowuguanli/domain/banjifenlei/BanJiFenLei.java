package com.zhizhao.jwgl.jiaowuguanli.domain.banjifenlei;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BanJiFenLei extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 名称
    @Column(unique = true)
    String mingCheng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJiFenLei that = (BanJiFenLei) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 844892873;
    }
}
