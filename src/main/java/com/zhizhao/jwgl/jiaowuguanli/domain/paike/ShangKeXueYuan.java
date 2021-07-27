package com.zhizhao.jwgl.jiaowuguanli.domain.paike;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShangKeXueYuan {
    //学员id
    @NotNull
    Long xueYuanId;
    //删除
    @NotNull
    @Column(columnDefinition = "boolean default false")
    Boolean isDeleted;

    // 上课学员类型
    @NotNull
    @Enumerated(EnumType.STRING)
    ShangKeXueYuanLeiXing shangKeXueYuanLeiXing;

    // 学员到课状态
    @Enumerated(EnumType.STRING)
    XueYuanDaoKeZhuangTai xueYuanDaoKeZhuangTai;

    // 备注
    String beiZhu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShangKeXueYuan that = (ShangKeXueYuan) o;

        if (!Objects.equals(xueYuanId, that.xueYuanId)) return false;
        if (!Objects.equals(isDeleted, that.isDeleted)) return false;
        return shangKeXueYuanLeiXing == that.shangKeXueYuanLeiXing;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(xueYuanId);
        result = 31 * result + (Objects.hashCode(isDeleted));
        result = 31 * result + (Objects.hashCode(shangKeXueYuanLeiXing));
        return result;
    }
}
