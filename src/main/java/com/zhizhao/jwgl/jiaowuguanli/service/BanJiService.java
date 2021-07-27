package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;

import java.util.List;

public interface BanJiService {
    IPage<DtoBanJi> huoQuBanJiLieBiao(Integer pageNum, Integer pageSize);
    DtoBanJi huoQuBanJiXiangQing(Long id);

    /**
     * 根据课程Id，获取班级列表
     * @param keChengId 课程Id
     * @return
     */
    List<DtoBanJi> huoQuBanJiByKeChengId(Long keChengId);

    /**
     * 添加班级学员
     * @param banJiId 班级Id
     * @param xueYuanId 学员Id
     */
    void tianJiaBanJiXueYuan(Long banJiId, Long xueYuanId);

    /**
     * 删除班级学员
     * @param banJiId 班级Id
     * @param xueYuanId 学员Id
     */
    void shanChuBanJiXueYuan(Long banJiId, Long xueYuanId);
}
