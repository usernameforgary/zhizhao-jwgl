package com.zhizhao.jwgl.jiaowuguanli.domain.banji;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BanJiXuYuan {
   //学员id
   Long xueYanId;
   //删除
   Boolean shanChu;
}
