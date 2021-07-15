package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * 班级排课信息
 */
@Entity
@Builder(toBuilder = true)
@TypeDef(name = "json", typeClass = JsonType.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BanJiPaiKeXinXi extends AggRoot {
    @Column(nullable = false)
    @Id
    @NotNull
    Long id;
    // 班级Id
    @NotNull
    Long banJiId;

    // 排课规则
    @Type(type = "json")
    @Column(columnDefinition = "json")
    PaiKeGuiZe paiKeGuiZe;

    // 上课老师
    Long shangKeLaoShiId;
    // 上课教室
    Long shangKeJiaoShiId;
    // 上课内容
    String shangKeNeiRong;

    /**
     * 创建班级排课信息
     * @param cmd
     * @return
     */
    public static BanJiPaiKeXinXi chuangJian(ChuangJianCommand cmd) {
        // TODO add more verification rules
        if(cmd.getPaiKeGuiZe() == null || cmd.getPaiKeGuiZe().getPaiKeFangShi() == null) {
            throw new BusinessException("请指定排课规则");
        }

        BanJiPaiKeXinXi banJiPaiKeXinXi = BanJiPaiKeXinXi.builder()
                .id(cmd.id)
                .banJiId(cmd.banJiId)
                .paiKeGuiZe(cmd.paiKeGuiZe)
                .shangKeLaoShiId(cmd.shangKeLaoShiId)
                .shangKeJiaoShiId(cmd.shangKeJiaoShiId)
                .shangKeNeiRong(cmd.shangKeNeiRong)
                .build();
        return banJiPaiKeXinXi;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCommand {
        @NotNull
        Long id;
        @NotNull
        Long banJiId;
        @NotNull
        PaiKeGuiZe paiKeGuiZe;
        @NotNull
        Long shangKeLaoShiId;
        @NotNull
        Long shangKeJiaoShiId;
        @NotNull
        @Size(min = 1, max = 50)
        String shangKeNeiRong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJiPaiKeXinXi that = (BanJiPaiKeXinXi) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1712168591;
    }
}
