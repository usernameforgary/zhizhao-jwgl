package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanJiXuYuan {
   //学员id
   @NotNull
   Long xueYuanId;
   //删除
   @NotNull
   @Column(columnDefinition = "boolean default false")
   Boolean shanChu;
}
