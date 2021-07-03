package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DingJiaBiaoZhun {
    //名称
    String mingCheng;
    //课时
    Double keShi;
    //总价
    Double zongJia;
    //课程单价
    Double keChengDanJia;
}
