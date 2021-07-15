package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaiKeJiLu extends AggRoot {
    @Column(nullable = false)
    @Id
    @NotNull
    Long id;
    // 班级排课信息Id
    @NotNull
    Long banJiPaiKeXinXiId;

    // 上课日期
    Long shangKeRiQi;

    // 上课老师
    @NotNull
    Long shangKeLaoShiId;
    // 上课教室
    Long shangKeJiaoShiId;

    // 上课开始时间
    Long shangKeShiJianStart;
    // 上课结束时间
    Long shangKeShiJianEnd;

    // 授课课时
    Double shouKeKeShi;

    // 上课内容
    @Size(max = 20)
    String shangKeNeiRong;

    // 排课记录状态
    @NotNull
    @Enumerated(EnumType.STRING)
    PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;

    // 上课学员
    @ElementCollection
    Set<ShangKeXueYuan> shangKeXueYuanZu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaiKeJiLu paiKeJiLu = (PaiKeJiLu) o;

        return Objects.equals(id, paiKeJiLu.id);
    }

    @Override
    public int hashCode() {
        return 904048910;
    }
}
