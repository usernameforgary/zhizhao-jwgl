package com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
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
 * 学员
 */
@Entity
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

   // 所属账号
   @Column(nullable = false)
   Long zhangHaoId;
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
