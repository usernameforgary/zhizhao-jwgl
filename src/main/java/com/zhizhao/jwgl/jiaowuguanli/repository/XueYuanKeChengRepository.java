package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XueYuanKeChengRepository extends JpaRepository<XueYuanKeCheng, Long> {
    /**
     * 根据id, 学员课程状态，查询不在该状态的学员课程
     * @param id
     * @param xueYuanKeChengZhuangTai
     * @return
     */
    XueYuanKeCheng findByIdAndKeChengZhuangTaiIsNotAndIsDeletedIsFalse(Long id, XueYuanKeChengZhuangTai xueYuanKeChengZhuangTai);

    /**
     * 根据id组获取学员课程
     *
     * @param ids 学员课程id列表
     * @return
     */
    List<XueYuanKeCheng> findByIdIn(List<Long> ids);

    /**
     * 根据学员Id和课程Id获取学员课程
     *
     * @param xueYuanId
     * @param keChengId
     * @return
     */
    XueYuanKeCheng findByXueYuanIdAndKeChengIdAndIsDeletedIsFalse(Long xueYuanId, Long keChengId);
}
