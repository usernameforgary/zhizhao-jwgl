package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YouHuiLeiXing;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name="json", typeClass = JsonType.class)
public class XueYuanKeCheng extends AggRoot {
    @Id
    @NotNull
    Long id;

    //学员ID
    @Column(nullable = false)
    Long xueYuanId;
    //课程ID
    @Column(nullable = false)
    Long keChengId;
    //定价标准
    @Type(type="json")
    @Column(columnDefinition = "json")
    DingJiaBiaoZhun dingJiaBiaoZhun;

    //课程状态
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    XueYuanKeChengZhuangTai keChengZhuangTai;
    //课程类型
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    KeChengLeiXing keChengLeiXing;
    //单价
    @Column(nullable = false)
    Double danJia;
    //课程数量
    @Column(nullable = false)
    Double keChengShuLiang;
    //赠送课时
    @Column(nullable = false)
    Double zengSongKeShi;
    //优惠类型
    @Enumerated(EnumType.STRING)
    YouHuiLeiXing youHuiLeiXing;
    //优惠数量
    Double youHuiShuLiang;

    // 备注
    String beiZhu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        XueYuanKeCheng that = (XueYuanKeCheng) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 847751178;
    }
}
