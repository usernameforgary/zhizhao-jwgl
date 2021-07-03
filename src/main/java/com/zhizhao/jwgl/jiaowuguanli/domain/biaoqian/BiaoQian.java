package com.zhizhao.jwgl.jiaowuguanli.domain.biaoqian;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BiaoQian extends AggRoot {
   @Id
   @NotNull
   Long id;

   //名称
   @Column(nullable = false, unique = true)
   String mingCheng;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      BiaoQian biaoQian = (BiaoQian) o;

      return getId() != null && getId().equals(biaoQian.getId());
   }

   @Override
   public int hashCode() {
      return 1106370586;
   }
}
