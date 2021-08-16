package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLuQuery;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.service.DianMingJiLuService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dianmingjilu")
public class DianMingJiLuController {

    @Autowired
    DianMingJiLuService dianMingJiLuService;

    /**
     * 获取点名记录
     * @param pageNum
     * @param pageSize
     * @param xueYuanId 学员Id
     * @param shangKeRiQiBegin
     * @param shangKeRiQiEnd
     * @param banJiId
     * @param shangKeLaoShiId
     * @return
     */
    @GetMapping("huoQuDianMingJiLuLieBiao")
    public PPResult huoQuDianMingJiLuLieBiao(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "20") Integer pageSize,
                                             @RequestParam(required = false) Long xueYuanId,
                                             @RequestParam(required = false) Long shangKeRiQiBegin,
                                             @RequestParam(required = false) Long shangKeRiQiEnd,
                                             @RequestParam(required = false) Long banJiId,
                                             @RequestParam(required = false) Long shangKeLaoShiId
                                            ) {
        DtoDianMingJiLuQuery dianMingJiLuQuery = new DtoDianMingJiLuQuery();
        dianMingJiLuQuery.setPageNum(pageNum);
        dianMingJiLuQuery.setPageSize(pageSize);
        dianMingJiLuQuery.setXueYuanId(xueYuanId);
        dianMingJiLuQuery.setShangKeRiQiBegin(shangKeRiQiBegin);
        dianMingJiLuQuery.setShangKeRiQiEnd(shangKeRiQiEnd);
        dianMingJiLuQuery.setBanJiId(banJiId);
        dianMingJiLuQuery.setShangKeLaoShiId(shangKeLaoShiId);

        DtoPageResult<DtoDianMingJiLu> dtoPageResult = dianMingJiLuService.huoQuDianMingJiLu(dianMingJiLuQuery);
        return PPResult.getPPResultOK(dtoPageResult);
    }
}
