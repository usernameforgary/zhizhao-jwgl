package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BanJiXuYuan {
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
}
