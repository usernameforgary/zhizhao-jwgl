package com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShouKuanFangShi;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 缴费记录
 */
@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class JiaoFeiJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 学员课程组
    @NotNull
    @ElementCollection
    @Column(name = "xueYuanKeChengId")
    List<Long> xueYuanKeChengZu;

    // 缴费记录状态
    @Enumerated(EnumType.STRING)
    JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai;

    // 跟进人
    Long genJinRenId;

    // 缴费历史记录
    @ElementCollection
    Set<JiaoFeiLiShi> jiaoFeiLiShiZu;

    // 创建
    public static JiaoFeiJiLu chuangJian(ChuangJianCmd cmd) {
        if(cmd.getXueYuanId() == null) {
            throw new BusinessException("缴费记录对应学员不能为空");
        }
        if(cmd.getJiaoFeiLiShiZu() == null || cmd.getJiaoFeiLiShiZu().size() == 0) {
            throw new BusinessException("缴费记录，缴费信息不能为空");
        }
        if(cmd.getJiaoFeiJiLuZhuangTai() == null) {
            throw new BusinessException("缴费记录状态不能为空");
        }
        JiaoFeiJiLu jiaoFeiJiLu = JiaoFeiJiLu.builder()
                .id(cmd.id)
                .xueYuanId(cmd.xueYuanId)
                .xueYuanKeChengZu(cmd.xueYuanKeChengZu)
                .jiaoFeiJiLuZhuangTai(cmd.jiaoFeiJiLuZhuangTai)
                .genJinRenId(cmd.genJinRenId)
                .jiaoFeiLiShiZu(cmd.jiaoFeiLiShiZu)
                .build();
        return jiaoFeiJiLu;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        Long xueYuanId;
        @NotNull
        List<Long> xueYuanKeChengZu;
        @NotNull
        JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai;
        @NotNull
        Long genJinRenId;
        @NotNull
        Set<JiaoFeiLiShi> jiaoFeiLiShiZu;
    }

    /**
     * 更改缴费记录状态
     * @param cmd
     */
    public void gengGaiJiaoFeiJiLuZhuangTai(GengGaiZhangTaiCmd cmd) {
       if(cmd.jiaoFeiJiLuZhuangTai == null)  {
           throw new BusinessException("请指定缴费记录状态");
       }
       setJiaoFeiJiLuZhuangTai(cmd.jiaoFeiJiLuZhuangTai);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GengGaiZhangTaiCmd {
        @NotNull
        JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JiaoFeiJiLu that = (JiaoFeiJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1476851835;
    }
}
