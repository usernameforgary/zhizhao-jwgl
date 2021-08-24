package com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GenJinZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YiXiangJiBie;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 学员
 */
@Entity
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"zhangHaoId", "xingMing"})
})
public class XueYuan extends AggRoot {
   @Id
   @NotNull
   Long id;

   // 所属账号Id
   @Column(nullable = false)
   Long zhangHaoId;
   // 姓名
   @Column(nullable = false)
   String xingMing;
   // 学员状态
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   XueYuanZhuangTai xueYuanZhuangTai;
   // 头像
   String touXiang;
   // 性别
   @Enumerated(EnumType.STRING)
   XingBie xingBie;
   // 年龄
   Double nanLing;
   // 就读学校
   String jiuDuXueXiao;
   // 当前年级
   String danqQianNianJi;
   // 家庭住址
   String jiaTingZhuZhi;
   // 学员来源
   String xueYuanLaiYuan;
   // 备注信息
   String beiZhuXinXi;
   // 标签
   @ElementCollection
   @Column(name = "biaoQianId")
   Set<Long> biaoQianZu;
   //跟进人
   Long genJinRenId;
   // 跟进状态
   @Enumerated(EnumType.STRING)
   GenJinZhuangTai genJinZhuangTai;
   // 意向级别
   @Enumerated(EnumType.STRING)
   YiXiangJiBie yiXiangJiBie;
   // 结业时间（学员状态为历史学员时存在）
   Long jieYeShiJian;

   // 创建
   public static XueYuan chuangJian(ChuangJianCmd cmd) {
      if(cmd.getXingMing() == null) {
         throw new BusinessException("请指定学员姓名");
      }
      if(cmd.getZhangHaoId() == null) {
         throw new BusinessException("请指定学员账户");
      }
      if(cmd.getXueYuanZhuangTai() == null) {
         throw new BusinessException("请指定学员状态");
      }
      XueYuan xueYuan = XueYuan.builder()
              .id(cmd.getId())
              .zhangHaoId(cmd.getZhangHaoId())
              .xingMing(cmd.getXingMing())
              .xueYuanZhuangTai(cmd.getXueYuanZhuangTai())
              .touXiang(cmd.getTouXiang())
              .xingBie(cmd.getXingBie())
              .nanLing(cmd.getNanLing())
              .jiuDuXueXiao(cmd.getJiuDuXueXiao())
              .danqQianNianJi(cmd.getDanqQianNianJi())
              .jiaTingZhuZhi(cmd.getJiaTingZhuZhi())
              .xueYuanLaiYuan(cmd.getXueYuanLaiYuan())
              .beiZhuXinXi(cmd.getBeiZhuXinXi())
              .biaoQianZu(cmd.getBiaoQianZu())
              .genJinRenId(cmd.getGenJinRenId())
              .build();
      return xueYuan;
   }

   // 更改学员状态
   public void gengGaiXueYuanZhuangTai(XueYuanZhuangTai xueYuanZhuangTai) {
      this.xueYuanZhuangTai = xueYuanZhuangTai;
   }

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public static class ChuangJianCmd {
      @NotNull
      Long id;
      // 所属账号Id
      Long zhangHaoId;
      // 姓名
      @NotNull
      String xingMing;
      // 学员状态
      @NotNull
      @Enumerated(EnumType.STRING)
      XueYuanZhuangTai xueYuanZhuangTai;
      // 头像
      String touXiang;
      // 性别
      @Enumerated(EnumType.STRING)
      XingBie xingBie;
      // 年龄
      Double nanLing;
      // 就读学校
      String jiuDuXueXiao;
      // 当前年级
      String danqQianNianJi;
      // 家庭住址
      String jiaTingZhuZhi;
      // 学员来源
      String xueYuanLaiYuan;
      // 备注信息
      String beiZhuXinXi;
      // 标签
      Set<Long> biaoQianZu;
      //跟进人
      Long genJinRenId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      XueYuan xueYuan = (XueYuan) o;

      return getId() != null && getId().equals(xueYuan.getId());
   }

   @Override
   public int hashCode() {
      return 66392343;
   }
}
