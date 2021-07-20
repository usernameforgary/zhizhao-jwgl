package com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 缴费记录
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
public class JiaoFeiJiLu extends AggRoot {
    @Column(nullable = false)
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 学员课程组
    @NotNull
    @ElementCollection
    @Column(name = "xueYuanKeChengId")
    List<Long> xueYuanKeChengZu;

    @NotNull
    // 实缴金额
    Double shiJiaoJinE;

    // 缴费记录状态
    @Enumerated
    JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai;

    // 跟进人
    Long genJinRenId;

    // 缴费历史记录
    @ElementCollection
    Set<JiaoFeiLishi> jiaoFeiLiShiZu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JiaoFeiJiLu that = (JiaoFeiJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1476851835;
    }
}
