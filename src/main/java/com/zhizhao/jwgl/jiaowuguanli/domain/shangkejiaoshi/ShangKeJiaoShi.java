package com.zhizhao.jwgl.jiaowuguanli.domain.shangkejiaoshi;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.banjifenlei.BanJiFenLei;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 上课教室
 */
@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ShangKeJiaoShi extends AggRoot {
    @Id
    @NotNull
    Long id;

    @Column(nullable = false, unique = true)
    String mingCheng;

    /**
     * 创建
     * @param cmd
     * @return
     */
    public static ShangKeJiaoShi chuangJian(ShangKeJiaoShi.ChuangJianCmd cmd) {
        ShangKeJiaoShi shangKeJiaoShi = ShangKeJiaoShi.builder()
                .id(cmd.getId())
                .mingCheng(cmd.getMingCheng())
                .build();
        return shangKeJiaoShi;
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
        if (cmd.getId() == null) {
            throw new BusinessException("请选择要更改的记录");
        }
        this.mingCheng = cmd.mingCheng;
        if(cmd.isDeleted != null) {
            this.setIsDeleted(cmd.isDeleted);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
        ShangKeJiaoShi that = (ShangKeJiaoShi) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 42209684;
    }
}
