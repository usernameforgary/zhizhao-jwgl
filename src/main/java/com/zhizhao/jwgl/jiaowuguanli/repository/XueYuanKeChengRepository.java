package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XueYuanKeChengRepository extends JpaRepository<XueYuanKeCheng, Long> {
    /**
     * 根据id, 学员课程状态，查询不在该状态的学员课程
     * @param id
     * @param xueYuanKeChengZhuangTai
     * @return
     */
    XueYuanKeCheng findByIdAndKeChengZhuangTaiIsNotAndIsDeletedIsFalse(Long id, XueYuanKeChengZhuangTai xueYuanKeChengZhuangTai);
}
