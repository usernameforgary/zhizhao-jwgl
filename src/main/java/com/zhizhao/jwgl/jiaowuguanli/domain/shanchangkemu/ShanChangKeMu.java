package com.zhizhao.jwgl.jiaowuguanli.domain.shanchangkemu;

import com.sun.istack.Nullable;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 擅长科目
 */
@Entity
@Builder(toBuilder = true)
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ShanChangKeMu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 名称
    @Column(nullable = false, unique = true)
    String minCheng;

    public static ShanChangKeMu chuangJian(ChuangJianCmd cmd) {
        ShanChangKeMu result = ShanChangKeMu.builder()
                .id(cmd.id)
                .minCheng(cmd.minCheng)
                .build();
        return result;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ChuangJianCmd {
        @Nullable
        Long id;
        @NotNull
        @Size(min = 1, max = 50)
        String minCheng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShanChangKeMu that = (ShanChangKeMu) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 988420432;
    }
}
