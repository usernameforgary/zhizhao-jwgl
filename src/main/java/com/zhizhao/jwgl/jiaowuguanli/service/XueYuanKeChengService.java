package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;

import java.util.List;

public interface XueYuanKeChengService {
    /**
     * 根据学员Id，获取学员已选课程
     * @param xueYuanId
     * @return
     */
    List<XueYuanKeCheng> getXueYuanKeChengByXueYuanId(Long xueYuanId);

    /**
     * 保存所有学员课程
     * @param xueYuanKeChengList
     * @return
     */
    List<XueYuanKeCheng> saveAllXueYuanKeCheng(List<XueYuanKeCheng> xueYuanKeChengList);
}
