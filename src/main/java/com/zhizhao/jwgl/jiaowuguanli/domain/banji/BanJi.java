package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.BanJiZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"keChengId", "mingCheng"})
})
public class BanJi extends AggRoot {
    @Id
    @NotNull
    Long id;

    //名称
    @Column(nullable = false)
    String mingCheng;
    //所属课程
    @Column(nullable = false)
    Long keChengId;
    //班级状态
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BanJiZhuangTai banJiZhuangTai = BanJiZhuangTai.KAI_KE;
    //班级老师
    Long banJiLaoShiId;
    //容量
    @Column(columnDefinition = "integer default 0")
    Integer rongLiang;
    //班级学员
    @ElementCollection
    Set<BanJiXueYuan> banJiXueYuanZu;
    //班级分类
    Long banJiFenLeiId;
    //上课教室
    Long shangKeJiaoShiId;
    //授课课时
    Double shouKeKeShi;
    //备注
    String beiZhu;
    // 已授课时
    Double yiShouKeShi;

    public static BanJi chuangJian(ChuangJianCmd cmd) {
        BanJi banJi = BanJi.builder()
                .id(cmd.getId())
                .mingCheng(cmd.getMingCheng())
                .keChengId(cmd.getKeChengId())
                .banJiZhuangTai(BanJiZhuangTai.KAI_KE)
                .banJiLaoShiId(cmd.banJiLaoShiId)
                .rongLiang(cmd.rongLiang)
                .banJiXueYuanZu(new HashSet<>())
                .banJiFenLeiId(cmd.banJiFenLeiId)
                .shangKeJiaoShiId(cmd.shangKeJiaoShiId)
                .shouKeKeShi(cmd.shouKeKeShi)
                .beiZhu(cmd.beiZhu)
                .build();
        return banJi;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        // 名称
        @NotNull
        @Size(min = 1, max = 50)
        String mingCheng;
        @NotNull
        Long keChengId;
        // 班级老师
        Long banJiLaoShiId;
        // 容量
        Integer rongLiang;
        Set<BanJiXueYuan> banJiXueYuanZu;
        // 班级分类
        Long banJiFenLeiId;
        // 上课教室
        Long shangKeJiaoShiId;
        // 默认授课课时
        Double shouKeKeShi;
        // 备注
        @Size(min = 1, max = 50)
        String beiZhu;
        // 已授课时
        Double yiShouKeShi;
    }

    /**
     * 添加学员
     * @param cmd
     */
    public void tianJiaXueYuan(TianJiaXueYuanCmd cmd) {
        if(cmd.xueYuanId == null) {
            throw new BusinessException("请指定要添加的学员");
        }
        if(banJiZhuangTai.equals(BanJiZhuangTai.JIE_KE)) {
            throw new BusinessException("班级已结课");
        }
        BanJiXueYuan newBanJiXueYuan = new BanJiXueYuan();
        newBanJiXueYuan.setXueYuanId(cmd.xueYuanId);
        newBanJiXueYuan.setIsDeleted(false);
        if(banJiXueYuanZu.contains(newBanJiXueYuan)) {
            throw new BusinessException("班级中已存在该学员");
        }
        banJiXueYuanZu.add(newBanJiXueYuan);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TianJiaXueYuanCmd {
        @NotNull
        Long xueYuanId;
    }

    /**
     * 删除学员
     * @param cmd
     */
    public void shanChuXueYuan(ShanChuXueYuanCmd cmd) {
        if(cmd.xueYuanId == null) {
            throw new BusinessException("请指定要添加的学员");
        }
        if(banJiZhuangTai.equals(BanJiZhuangTai.JIE_KE)) {
            throw new BusinessException("班级已结课");
        }
        BanJiXueYuan banJiXueYuan = new BanJiXueYuan();
        banJiXueYuan.setXueYuanId(cmd.xueYuanId);
        banJiXueYuan.setIsDeleted(false);
        BanJiXueYuan existBanJiXueYuan = null;
        if(!banJiXueYuanZu.contains(banJiXueYuan)) {
            throw new BusinessException("当前班级中未找到该学员");
        }
        for(BanJiXueYuan banJiXueYuan1: banJiXueYuanZu) {
            if(banJiXueYuan1.equals(banJiXueYuan)) {
                existBanJiXueYuan = banJiXueYuan1;
                return;
            }
        }
        if(existBanJiXueYuan != null) {
            existBanJiXueYuan.setIsDeleted(true);
        } else {
            throw new BusinessException("当前班级中未找到该学员");
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShanChuXueYuanCmd {
        @NotNull
        Long xueYuanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BanJi banJi = (BanJi) o;

        return getId() != null && getId().equals(banJi.getId());
    }

    @Override
    public int hashCode() {
        return 2132218027;
    }
}
