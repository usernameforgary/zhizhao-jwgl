package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name="json", typeClass = JsonType.class)
public class KeCheng extends AggRoot {
    @Id
    @NotNull
    Long id;

    //名称
    @Column(nullable = false, unique = true)
    String mingCheng;
    //单价
    @Column(nullable = false)
    Double danJia;
    //启用状态
    @Column(nullable = false, columnDefinition = "boolean default true")
    Boolean qiYongZhuangTai;
    //定价标准
    @Type(type="json")
    @Column(columnDefinition = "json")
    List<DingJiaBiaoZhun> dingJiaBiaoZhunZu;
    //请假扣课时
    @Column(nullable = true)
    Boolean qingJiaKouKeShi = false;
    //未到扣课时
    @Column(nullable = true)
    Boolean weiDaoKouKeShi = false;
    //备注
    String beiZhu;

    public static KeCheng chuangJian(ChuangJianCmd cmd) {
        KeCheng result = KeCheng.builder()
                .id(cmd.getId())
                .mingCheng(cmd.getMingCheng())
                .danJia(cmd.getDanJia())
                .qiYongZhuangTai(true)
                .dingJiaBiaoZhunZu(cmd.getDingJiaBiaoZhunZu())
                .qingJiaKouKeShi(cmd.getQingJiaKouKeShi())
                .weiDaoKouKeShi(cmd.getWeiDaoKouKeShi())
                .beiZhu(cmd.getBeiZhu())
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
        String mingCheng;
        @NotNull
        Double danJia;
        List<DingJiaBiaoZhun> dingJiaBiaoZhunZu;
        @NotNull
        Boolean qingJiaKouKeShi;
        @NotNull
        Boolean weiDaoKouKeShi;
        String beiZhu;
    }

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
