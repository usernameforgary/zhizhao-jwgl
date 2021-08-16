package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;

public interface PaiKeJiLuMapper extends MyBaseMapper<PaiKeJiLu> {
    /**
     * 分页获取排课记录
     * @param page 分页信息
     * @param paiKeJiLuZhuangTai 排课记录状态
     * @param shangKeRiQiBegin 上课日期 开始
     * @param shangKeRiQiEnd   上课日期 结束
     * @param banJiId          班级Id
     * @param shangKeLaoShiId  上课老师Id
     * @return
     */
    IPage<DtoPaiKeJiLu> getPaiKeJiLuList(IPage page, String paiKeJiLuZhuangTai, Long shangKeRiQiBegin, Long shangKeRiQiEnd, Long banJiId, Long shangKeLaoShiId);
}
