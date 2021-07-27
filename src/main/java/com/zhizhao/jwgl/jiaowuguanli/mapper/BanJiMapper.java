package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;

import java.util.List;

public interface BanJiMapper extends MyBaseMapper<BanJi> {
    IPage<DtoBanJi> huoQuBanJiLeiBiao(IPage page);
    DtoBanJi huoQuBanJiXiangQing(Long id);

    /**
     * 根据课程Id，获取班级列表
     * @param keChengId 课程Id
     * @return
     */
    List<DtoBanJi> huoQuBanJiByKeChengId(Long banJiId);
}
