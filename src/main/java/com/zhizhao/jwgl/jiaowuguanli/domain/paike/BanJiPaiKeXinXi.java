package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
    private Long id;
    // 班级Id
    @NotNull
    private Long banJiId;

    // 排课规则
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> paiKeGuiZe;

    // 上课老师
    Long shangKeLaoShiId;
    // 上课教室
    Long shangKeJiaoShiId;
    // 上课内容
    String shangKeNeiRong;

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
