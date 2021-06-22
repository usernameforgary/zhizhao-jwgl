package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class KeCheng {
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

    //名称
    @Column(nullable = false)
    String mingCheng;
    //单价
    @Column(nullable = false)
    Double danJia;
    //启用状态
    @Column(nullable = false)
    Boolean qiYongZhuangTai = true;
    //定价标准
    @ElementCollection
    Set<DingJiaBiaoZhun> dingJiaBiaoZhunZu;
    //请假扣课时
    @Column(nullable = false)
    Boolean qingJiaKouKeShi = false;
    //未到扣课时
    @Column(nullable = false)
    Boolean WeiDaoKouKeShi = false;
    //备注
    String beiZhu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KeCheng keCheng = (KeCheng) o;

        return getId() != null && getId().equals(keCheng.getId());
    }

    @Override
    public int hashCode() {
        return 815518512;
    }
}
