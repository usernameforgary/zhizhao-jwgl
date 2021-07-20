package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.KeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;

import java.util.List;

public interface KeChengService {
    IPage<DtoKeCheng> huoQuKeChengLieBiao(Integer pageNum, Integer pageSize);

    /**
     * 根据学员Id获取当前学员未报名的课程，如果学员Id为空，则返回所有课程
     * @param xueYuanId
     * @return
     */
    List<KeCheng> getWeiXuanKeChengListByXueYuanId(Long xueYuanId);
}
