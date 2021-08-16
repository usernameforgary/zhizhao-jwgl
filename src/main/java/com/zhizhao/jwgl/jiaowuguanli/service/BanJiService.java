package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiXueYuan;

import java.util.List;

public interface BanJiService {
    IPage<DtoBanJi> huoQuBanJiLieBiao(Integer pageNum, Integer pageSize);
    DtoBanJi huoQuBanJiXiangQing(Long id);

    /**
     * 根据课程Id，获取选择了该课程的班级列表
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

    /**
     * 根据班级id，获取班级学员
     * @param banJiId 班级Id
     * @return
     */
    List<DtoBanJiXueYuan> huoQuBanJiXueYuanByBanJiId(Long banJiId);

    /**
     * 根据Id获取班级
     * @param id
     * @return
     */
    BanJi getBanJiById(Long id);

    /**
     * 根据学员Id，获取班级列表
     * @param xueYuanId 学员Id
     * @return
     */
    List<DtoBanJi> huoQuBanJiByXueYuanId(Long xueYuanId);
}
