package com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjiluwenjian;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
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
public class ChengZhangJiLuWenJian extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 名称
    String mingCheng;

    // 后缀
    @NotNull
    String houZhui;

    // oss上key
    @NotNull
    String ossKey;

    // oss的bucket名称
    String ossBucketName;

    // 大小
    @NotNull
    Integer daXiao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChengZhangJiLuWenJian that = (ChengZhangJiLuWenJian) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 28131706;
    }
}
