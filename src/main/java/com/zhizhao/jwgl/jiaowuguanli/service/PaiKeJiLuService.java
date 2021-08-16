package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLuQuery;

import java.util.List;

public interface PaiKeJiLuService {
    /**
     * 保存所有排课记录
     * @param paiKeJiLuList
     */
    void saveAllPaiKeJiLu(List<PaiKeJiLu> paiKeJiLuList);

    /**
     * 根据排课信息Id，获取所有（未删除，未点名，上课时间晚于当前时间）的排课记录
     * @param paiKeXinXiIdList 排课信息Id列表
     * @return
     */
    List<PaiKeJiLu> getAllByPaiKeXinXinId(List<Long> paiKeXinXiIdList);

    /**
     * 分页获取排课记录
     * @param dtoPaiKeJiLuQuery 查询参数Dto
     * @return
     */
    DtoPageResult<DtoPaiKeJiLu> getPaiKeJiLuList(DtoPaiKeJiLuQuery dtoPaiKeJiLuQuery);

    /**
     * 根据Id获取排课记录
     * @param id 排课记录Id
     * @return
     */
    PaiKeJiLu getPaiKeJiLuById(Long id);
}
