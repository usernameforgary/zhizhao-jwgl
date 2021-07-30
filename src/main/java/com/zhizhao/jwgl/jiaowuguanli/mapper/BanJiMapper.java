package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiXueYuan;

import java.util.List;

public interface BanJiMapper extends MyBaseMapper<BanJi> {
    IPage<DtoBanJi> huoQuBanJiLeiBiao(IPage page);
    DtoBanJi huoQuBanJiXiangQing(Long id);

    /**
     * 根据课程Id，获取选择了该课程的班级列表
     * @param keChengId 课程Id
     * @return
     */
    List<DtoBanJi> huoQuBanJiByKeChengId(Long keChengId);

    /**
     * 根据班级id，获取班级学员
     *
     * @param banJiId 班级Id
     * @return
     */
    List<DtoBanJiXueYuan> huoQuBanJiXueYuanByBanJiId(Long banJiId);
}
