package com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 账号
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ZhangHao {
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
    @NotNull
    Boolean isDeleted = false;

    // 姓名
    @Column(nullable = false)
    String xingMing;
    // 手机
    @Column(nullable = false, unique = true)
    String shouJi;
    // 性别
    @Enumerated(EnumType.STRING)
    XingBie xingBie;
    // 密码
    String miMa;
    // 账号角色
    @ElementCollection
    @Column(name = "jueSeId")
    Set<Long> jueSeZu;
    // 账号类型
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ZhangHaoLeiXing zhangHaoLeiXing;
    // 是否授课
    @Column(nullable = false)
    Boolean isLaoShi = false;
    // 备注
    String beiZhu;
    // 在职状态
    @Column(nullable = false)
    Boolean zaiZhiZhuangTai = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ZhangHao zhangHao = (ZhangHao) o;

        return getId() != null && getId().equals(zhangHao.getId());
    }

    @Override
    public int hashCode() {
        return 155080257;
    }
}
