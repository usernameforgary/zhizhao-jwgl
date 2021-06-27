package com.zhizhao.jwgl.jiaowuguanli.domain.juese;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class JueSe {
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
    @Column(columnDefinition = "boolean default false")
    Boolean isDeleted = false;

    // 名称
    @Column(nullable = false, unique = true)
    String mingCheng;
    // 简介
    String jianJie;
    // 系统菜单
    @ElementCollection
    @Column(name = "xiTongCaiDanId")
    Set<Long> xiTongCaiDanZu;
    // 系统Api
    @ElementCollection
    @Column(name = "xiTongApiId")
    Set<Long> xiTongApiZu;

    public static JueSe chuangJian(ChuangJianCmd cmd) {
        JueSe result = JueSe.builder()
                .id(cmd.id)
                .mingCheng(cmd.mingCheng)
                .jianJie(cmd.jianJie)
                .xiTongCaiDanZu(cmd.xiTongCaiDanZu)
                .xiTongApiZu(cmd.xiTongApiZu)
                .build();
        return result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        Boolean isDelete = false;
        @NotNull
        @Size(min = 1, max = 50)
        String mingCheng;
        @NotNull
        @Size(min = 1, max = 50)
        String jianJie;
        @NotNull
        Set<Long> xiTongCaiDanZu;
        Set<Long> xiTongApiZu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JueSe jueSe = (JueSe) o;

        return getId() != null && getId().equals(jueSe.getId());
    }

    @Override
    public int hashCode() {
        return 1117681306;
    }
}
