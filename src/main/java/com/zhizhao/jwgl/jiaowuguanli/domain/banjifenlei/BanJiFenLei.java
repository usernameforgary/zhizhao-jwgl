package com.zhizhao.jwgl.jiaowuguanli.domain.banjifenlei;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BanJiFenLei extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 名称
    @Column(unique = true)
    String mingCheng;

    /**
     * 创建
     * @param cmd
     * @return
     */
    public static BanJiFenLei chuangJian(ChuangJianCmd cmd) {
        BanJiFenLei banJiFenLei = BanJiFenLei.builder()
                .id(cmd.getId())
                .mingCheng(cmd.getMingCheng())
                .build();
        return banJiFenLei;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        @Size(min = 1, max = 50)
        String mingCheng;
    }

    /**
     * 更新
     * @param cmd
     */
    public void gengXin(GengXinCmd cmd) {
        this.mingCheng = cmd.mingCheng;
        if(cmd.isDeleted != null) {
            this.setIsDeleted(cmd.isDeleted);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GengXinCmd {
        @NotNull
        Long id;
        @NotNull
        @Size(min = 1, max = 50)
        String mingCheng;
        Boolean isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJiFenLei that = (BanJiFenLei) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 844892873;
    }
}
