package com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 系统菜单
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Builder(toBuilder = true)
public class XiTongCaiDan {
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

    // 直接父级菜单Id
    Long fuId;
    // 当前菜单所有父级菜单, [fuId1][fuId2]
    String fuIds;
    // 菜单名称
    String mingCheng;
    // 菜单路由
    @Column(nullable = false)
    String url;
    // 菜单排序
    @Column(nullable = false)
    Integer paiXu;
    // 隐藏
    Boolean yinCang= false;
    // 菜单图标
    String tuBiao;
    // 是不是叶子节点
    Boolean isYeZi = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        XiTongCaiDan that = (XiTongCaiDan) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 973547846;
    }
}

