package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.dianmingjilu.DianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLu;

public interface DianMingJiLuMapper extends MyBaseMapper<DianMingJiLu> {
    /**
     * 分页获取排课记录
     * @param page 分页信息
     * @param xueYuanId 学员Id
     * @param shangKeRiQiBegin 上课日期 开始
     * @param shangKeRiQiEnd   上课日期 结束
     * @param banJiId          班级Id
     * @param shangKeLaoShiId  上课老师Id
     * @return
     */
    IPage<DtoDianMingJiLu> getDianMingJiLuList(IPage page, Long xueYuanId, Long shangKeRiQiBegin, Long shangKeRiQiEnd, Long banJiId, Long shangKeLaoShiId);
}
