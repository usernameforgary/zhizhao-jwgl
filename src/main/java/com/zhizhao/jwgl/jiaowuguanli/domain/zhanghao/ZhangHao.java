package com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.MyStringUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * 账号
 */
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ZhangHao extends AggRoot {
    @Id
    @NotNull
    Long id;

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

    // 创建
    public static ZhangHao chuangJian(ChuangJianCmd cmd) {
        if(StringUtils.isEmpty(cmd.getShouJi())) {
            throw new BusinessException("请提供正确的手机号");
        }
        if(!MyStringUtil.isValidShouJi(cmd.getShouJi())) {
            throw new BusinessException("请提供正确的手机号");
        }
        if(cmd.getZhangHaoLeiXing() == null) {
            throw new BusinessException("请指定当前账户类型");
        }

        ZhangHao zhangHao = ZhangHao.builder()
                .id(cmd.id)
                .xingMing(cmd.xingMing)
                .shouJi(cmd.shouJi)
                .xingBie(cmd.xingBie)
                .miMa(cmd.miMa)
                .jueSeZu(cmd.jueSeZu)
                .zhangHaoLeiXing(cmd.zhangHaoLeiXing)
                .isLaoShi(cmd.isLaoShi)
                .beiZhu(cmd.beiZhu)
                .zaiZhiZhuangTai(true)
                .build();
        return zhangHao;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        @Size(min = 1, max = 50)
        String xingMing;
        @NotNull
        @Size(min = 1, max = 50)
        String shouJi;
        @NotNull
        XingBie xingBie;
        @NotNull
        @Size(min = 1, max = 50)
        String miMa;
        @NotNull
        Set<Long> jueSeZu;
        @NotNull
        ZhangHaoLeiXing zhangHaoLeiXing;
        @NotNull
        Boolean isLaoShi;
        @Size(min = 1, max = 50)
        String beiZhu;
    }

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
