package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;

import java.util.List;

public interface PaiKeJiLuMapper extends MyBaseMapper<PaiKeJiLu> {
    /**
     * 分页获取排课记录
     * @param page 分页信息
     * @param paiKeJiLuZhuangTaiZu 排课记录状态
     * @param shangKeRiQiBegin 上课日期 开始
     * @param shangKeRiQiEnd   上课日期 结束
     * @param banJiId          班级Id
     * @param shangKeLaoShiId  上课老师Id
     * @return
     */
    IPage<DtoPaiKeJiLu> getPaiKeJiLuList(IPage page, List<String> paiKeJiLuZhuangTaiZu, Long shangKeRiQiBegin, Long shangKeRiQiEnd, Long banJiId, Long shangKeLaoShiId);

    /**
     * 根据排课记录Id，查询排课记录，课后点评
     *
     * @param id
     * @return
     */
    DtoPaiKeJiLu getPaiKeJiLuKeHouDianPingById(Long id);

    /**
     * 根据排课信息Id组，上课学员Id。获取包含该学员的，所有【待点名】的上课排课记录
     *
     * @param paiKeXinXinXiIdList 排课信息Id组
     * @param shangKeXueYuanId 上课学员Id
     * @return
     */
    List<PaiKeJiLu> getPaiKeJiLuByPaiKeXinXiIdZuAndShangKeXueYuan(List<Long> paiKeXinXinXiIdList, Long shangKeXueYuanId);
}
