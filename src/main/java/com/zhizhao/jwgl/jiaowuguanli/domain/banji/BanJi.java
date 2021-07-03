package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.BanJiZhuangTai;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BanJi extends AggRoot {
    @Id
    @NotNull
    Long id;

    //名称
    @Column(nullable = false)
    String mingChen;
    //所属课程
    @Column(nullable = false)
    Long keChengId;
    //班级状态
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BanJiZhuangTai banJiZhuangTai = BanJiZhuangTai.KAI_KE;
    //班级老师
    Long laoShiId;
    //容量
    Integer rongLiang;
    //班级学员
    @ElementCollection
    Set<BanJiXuYuan> banJiXueYuanZu;
    //班级分类
    Long banJiFenLeiId;
    //上课教室
    Long shangKeJiaoShiId;
    //授课课时
    Double shouKeKeShi;
    //备注
    String beiZhu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJi banJi = (BanJi) o;

        return getId() != null && getId().equals(banJi.getId());
    }

    @Override
    public int hashCode() {
        return 2132218027;
    }
}
