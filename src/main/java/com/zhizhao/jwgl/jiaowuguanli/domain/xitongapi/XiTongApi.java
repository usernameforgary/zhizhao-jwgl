package com.zhizhao.jwgl.jiaowuguanli.domain.xitongapi;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 系统API
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class XiTongApi extends AggRoot {
    @Id
    @NotNull
    Long id;

    /**
     * 接口父ID(即接口分组)
     */
    private Long apiPid;

    /**
     * 当前接口的所有上级id(即所有上级分组)
     */
    private String apiPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Boolean isLeaf = false;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 跳转URL
     */
    @Column(unique = true)
    private String url;

    /**
     * 排序
     */
    private Integer apiSort;

    /**
     * 层级，1：接口分组，2：接口
     */
    private Integer level;

    /**
     * 是否禁用，0:启用(否）,1:禁用(是)
     */
    private Boolean status = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        XiTongApi xiTongApi = (XiTongApi) o;

        return getId() != null && getId().equals(xiTongApi.getId());
    }

    @Override
    public int hashCode() {
        return 348536734;
    }
}
