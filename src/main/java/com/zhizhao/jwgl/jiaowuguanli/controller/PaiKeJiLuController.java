package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLuQuery;
import com.zhizhao.jwgl.jiaowuguanli.service.PaiKeJiLuService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paikejilu")
public class PaiKeJiLuController {

    @Autowired
    PaiKeJiLuService paiKeJiLuService;

    /**
     * 获取排课记录列表
     * @param pageNum
     * @param pageSize
     * @param shangKeRiQiBegin 上课日期 开始
     * @param shangKeRiQiEnd 上课日期 结束
     * @param banJiId 班级Id
     * @param shangKeLaoShiId 上课老师Id
     * @param paiKeJiLuZhuangTaiZu 排课记录状态列表（[待点名 | 已点名 | 已点评]）
     * @return
     */
    @GetMapping("huoQuPaiKeJiLu")
    public PPResult huoQuPaiKeJiLu(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "20") Integer pageSize,
                                   @RequestParam(required = false) Long shangKeRiQiBegin,
                                   @RequestParam(required = false) Long shangKeRiQiEnd,
                                   @RequestParam(required = false) Long banJiId,
                                   @RequestParam(required = false) Long shangKeLaoShiId,
                                   @RequestParam(required = false) List<PaiKeJiLuZhuangTai> paiKeJiLuZhuangTaiZu) {
        DtoPaiKeJiLuQuery dtoPaiKeJiLuQuery = new DtoPaiKeJiLuQuery();
        dtoPaiKeJiLuQuery.setPageNum(pageNum);
        dtoPaiKeJiLuQuery.setPageSize(pageSize);
        dtoPaiKeJiLuQuery.setShangKeRiQiBegin(shangKeRiQiBegin);
        dtoPaiKeJiLuQuery.setShangKeRiQiEnd(shangKeRiQiEnd);
        dtoPaiKeJiLuQuery.setBanJiId(banJiId);
        dtoPaiKeJiLuQuery.setShangKeLaoShiId(shangKeLaoShiId);
        dtoPaiKeJiLuQuery.setPaiKeJiLuZhuangTaiZu(paiKeJiLuZhuangTaiZu);

        DtoPageResult<DtoPaiKeJiLu> dtoPageResult =  paiKeJiLuService.getPaiKeJiLuList(dtoPaiKeJiLuQuery);
        return PPResult.getPPResultOK(dtoPageResult);
    }

    /**
     * 根据排课记录Id，查询排课记录，课后点评
     * @param id
     * @return
     */
    @GetMapping("huoQuPaiKeJiLuKeHouDianPingById")
    public PPResult huoQuPaiKeJiLuKeHouDianPingById(@RequestParam Long id) {
        DtoPaiKeJiLu dtoPaiKeJiLu = paiKeJiLuService.getPaiKeJiLuKeHouDianPingById(id);
        return PPResult.getPPResultOK(dtoPaiKeJiLu);
    }
}
