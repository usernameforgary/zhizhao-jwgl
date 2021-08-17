package com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ChengZhangJiLuLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class ChengZhangJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 内容
    @NotNull
    String neiRong;

    // 成长记录文件组
    @ElementCollection
    @Column(name = "chengZhangJiLuWenJianId")
    Set<Long> chengZhangJiLuWenJianZu;

    // 排课记录Id，成长记录类型 = [KE_HOU_DIAN_PING： 课后点评]
    Long paiKeJiLuId;

    // 成长记录类型
    @NotNull
    @Enumerated(EnumType.STRING)
    ChengZhangJiLuLeiXing chengZhangJiLuLeiXing;

    // 家长已读
    @Column(columnDefinition = "boolean default false")
    Boolean jiaZhangYiDu = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChengZhangJiLu that = (ChengZhangJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1723882039;
    }
}
