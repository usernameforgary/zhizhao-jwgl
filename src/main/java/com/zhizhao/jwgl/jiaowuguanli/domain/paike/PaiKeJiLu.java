package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaiKeJiLu extends AggRoot {
    @Column(nullable = false)
    @Id
    @NotNull
    Long id;
    // 班级排课信息Id
    @NotNull
    Long banJiPaiKeXinXiId;

    // 上课日期
    Long shangKeRiQi;

    // 上课老师
    Long shangKeLaoShiId;
    // 上课教室
    Long shangKeJiaoShiId;

    // 上课开始时间
    Long shangKeShiJianStart;
    // 上课结束时间
    Long shangKeShiJianEnd;

    // 授课课时
    Double shouKeKeShi;

    // 上课内容
    @Size(max = 20)
    String shangKeNeiRong;

    // 排课记录状态
    @NotNull
    @Enumerated(EnumType.STRING)
    PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;

    // 上课学员
    @ElementCollection
    Set<ShangKeXueYuan> shangKeXueYuanZu;

    // 点名时间
    Long dianMingShiJian;

    /**
     * 创建排课记录
     * @param cmd
     * @return
     */
    public static PaiKeJiLu chuangJian(ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            throw new BusinessException("请指定Id");
        }
        if(cmd.getBanJiPaiKeXinXiId() == null) {
            throw new BusinessException("未指定对应排课信息");
        }

        PaiKeJiLu paiKeJiLu = PaiKeJiLu.builder()
                .id(cmd.id)
                .banJiPaiKeXinXiId(cmd.banJiPaiKeXinXiId)
                .shangKeRiQi(cmd.shangKeRiQi)
                .shangKeLaoShiId(cmd.shangKeLaoShiId)
                .shangKeJiaoShiId(cmd.shangKeJiaoShiId)
                .shangKeShiJianStart(cmd.shangKeShiJianStart)
                .shangKeShiJianEnd(cmd.shangKeShiJianEnd)
                .shouKeKeShi(cmd.shouKeKeShi)
                .shangKeNeiRong(cmd.shangKeNeiRong)
                .paiKeJiLuZhuangTai(PaiKeJiLuZhuangTai.DAI_DIAN_MING)
                .shangKeXueYuanZu(cmd.shangKeXueYuanZu)
                .dianMingShiJian(cmd.dianMingShiJian)
                .build();
        return paiKeJiLu;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ChuangJianCmd {
        Long id;
        @NotNull
        Long banJiPaiKeXinXiId;
        // 上课日期
        Long shangKeRiQi;
        // 上课老师
        Long shangKeLaoShiId;
        // 上课教室
        Long shangKeJiaoShiId;
        // 上课开始时间
        Long shangKeShiJianStart;
        // 上课结束时间
        Long shangKeShiJianEnd;
        // 授课课时
        Double shouKeKeShi;
        // 上课内容
        String shangKeNeiRong;
        // 排课记录状态
        @NotNull
        PaiKeJiLuZhuangTai paiKeJiLuZhuangTai;
        // 上课学员
        @ElementCollection
        Set<ShangKeXueYuan> shangKeXueYuanZu;
        // 点名时间
        Long dianMingShiJian;
    }

    /**
     * 添加上课学员
     * @param cmd
     */
    public void tianJiaShangKeXueYuan(TianJiaShangKeXueYuanCmd cmd) {
        if(cmd.xueYuanId == null)  {
            throw new BusinessException("请指定上课学员");
        }
        if(cmd.isDeleted == null) {
            throw new BusinessException("请指定上课学员状态");
        }
        if(cmd.shangKeXueYuanLeiXing == null) {
            throw new BusinessException("请指定上课学员类型");
        }
        ShangKeXueYuan shangKeXueYuan = new ShangKeXueYuan();
        shangKeXueYuan.setXueYuanId(cmd.xueYuanId);
        shangKeXueYuan.setIsDeleted(cmd.isDeleted);
        shangKeXueYuan.setShangKeXueYuanLeiXing(cmd.shangKeXueYuanLeiXing);
        if(shangKeXueYuanZu.contains(shangKeXueYuan)) {
            throw new BusinessException("排课记录中，该学员已存在");
        }
        shangKeXueYuanZu.add(shangKeXueYuan);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TianJiaShangKeXueYuanCmd {
        @NotNull
        Long xueYuanId;
        @NotNull
        Boolean isDeleted;
        @NotNull
        ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;
    }

    /**
     * 删除上课学员
     * @param cmd
     */
    public void shanChuShanKeXueYuan(ShanChuXueYuanCmd cmd) {
        if(cmd.xueYuanId == null)  {
            throw new BusinessException("请指定上课学员");
        }
        if(cmd.isDeleted == null) {
            throw new BusinessException("请指定上课学员状态");
        }
        if(cmd.shangKeXueYuanLeiXing == null) {
            throw new BusinessException("请指定上课学员类型");
        }
        ShangKeXueYuan shangKeXueYuan = new ShangKeXueYuan();
        shangKeXueYuan.setXueYuanId(cmd.xueYuanId);
        shangKeXueYuan.setIsDeleted(cmd.isDeleted);
        shangKeXueYuan.setShangKeXueYuanLeiXing(cmd.shangKeXueYuanLeiXing);
        ShangKeXueYuan existShangKeXueYuan = null;
        if(!shangKeXueYuanZu.contains(shangKeXueYuan)) {
            throw new BusinessException("上课学员中，不存在该学员");
        }
        for(ShangKeXueYuan shangKeXueYuan1 : shangKeXueYuanZu) {
            if(shangKeXueYuan1.equals(shangKeXueYuan)) {
                existShangKeXueYuan = shangKeXueYuan1;
                return;
            }
        }
        if(existShangKeXueYuan == null) {
            throw new BusinessException("上课学员中，未找到该学员");
        } else {
            existShangKeXueYuan.setIsDeleted(true);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShanChuXueYuanCmd {
        @NotNull
        Long xueYuanId;
        @NotNull
        Boolean isDeleted;
        @NotNull
        ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaiKeJiLu paiKeJiLu = (PaiKeJiLu) o;

        return Objects.equals(id, paiKeJiLu.id);
    }

    @Override
    public int hashCode() {
        return 904048910;
    }
}
