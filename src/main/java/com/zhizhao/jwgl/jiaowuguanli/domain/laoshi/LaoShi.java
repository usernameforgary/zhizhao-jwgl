package com.zhizhao.jwgl.jiaowuguanli.domain.laoshi;

import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
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
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class LaoShi {
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

    //所属账号
    @Column(nullable = false)
    Long zhangHaoId;
    //擅长科目组
    @ElementCollection
    @Column(name = "shanChangKeMuId")
    Set<Long> shanChangKeMuZu;

    // 创建老师
    public static LaoShi chuangJian(ChuangJianCmd cmd) {
        if(cmd.getZhangHaoId() == null) {
            throw new BusinessException("老师账号创建失败");
        }

        LaoShi laoShi = LaoShi.builder()
                .id(cmd.id)
                .isDeleted(false)
                .zhangHaoId(cmd.zhangHaoId)
                .shanChangKeMuZu(cmd.shanChangKeMuZu)
                .build();
        return laoShi;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        Long zhangHaoId;
        @NotNull
        Set<Long> shanChangKeMuZu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LaoShi laoShi = (LaoShi) o;

        return getId() != null && getId().equals(laoShi.getId());
    }

    @Override
    public int hashCode() {
        return 722388156;
    }
}
