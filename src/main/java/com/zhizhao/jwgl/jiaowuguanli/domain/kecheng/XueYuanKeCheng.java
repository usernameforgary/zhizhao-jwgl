package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YouHuiLeiXing;
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
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class XueYuanKeCheng {
    @Id
    @NotNull
    Long id;
    @CreatedDate
    @NotNull
    Long createTime;
    @LastModifiedDate
    Long updateTime;
    @Version
    Integer version;
    Boolean isDeleted = false;

    //学员ID
    @Column(nullable = false)
    Long xueYuanId;
    //课程ID
    @Column(nullable = false)
    Long keChengId;
    //定价标准
    @Embedded
    DingJiaBiaoZhun dingJiaBiaoZhun;

    //课程状态
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    KeChengZhuangTai keChengZhuangTai;
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
    //原价
    @Column(nullable = false)
    Double yuanJia;
    //赠送课时
    @Column(nullable = false)
    Double zengSongKeShi;
    //优惠类型
    @Enumerated(EnumType.STRING)
    YouHuiLeiXing youHuiLeiXing;
    //优惠数量
    Double youHuiShuLiang;
    //签约金额
    @Column(nullable = false)
    Double qianYueJinE;

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