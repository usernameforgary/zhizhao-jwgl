package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Data
public class BanJiXueYuan {
   //学员id
   @NotNull
   Long xueYuanId;
   //删除
   @NotNull
   @Column(columnDefinition = "boolean default false")
   Boolean isDeleted;
   @CreatedDate
   @NotNull
   Long createdAt;
   @LastModifiedDate
   Long updateTime;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      BanJiXueYuan that = (BanJiXueYuan) o;

      if (!Objects.equals(xueYuanId, that.xueYuanId)) return false;
      return Objects.equals(isDeleted, that.isDeleted);
   }

   @Override
   public int hashCode() {
      int result = Objects.hashCode(xueYuanId);
      result = 31 * result + (Objects.hashCode(isDeleted));
      return result;
   }
}
