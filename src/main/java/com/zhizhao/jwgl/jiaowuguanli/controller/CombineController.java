package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;
import com.zhizhao.jwgl.jiaowuguanli.service.CombineService;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("combine")
public class CombineController {

    @Autowired
    YuanGongService yuanGongService;
    @Autowired
    CombineService combineService;

    @PostMapping("tianjiaYuanGong")
    public PPResult tainJiaYuanGong(@Valid @RequestBody DtoTianJiaYuanGong dto) {
        yuanGongService.tianJianYuanGong(dto);
        return PPResult.Ok();
    }

    /**
     * 创建班级排课信息
     * @param cmd
     * @return
     */
    @PostMapping("chuangJianBanJiPaiKeXinXi")
    public PPResult tianJiaBanJiPaiKeXinXi(@Valid @RequestBody BanJiPaiKeXinXi.ChuangJianCommand cmd) {
        combineService.chuangJianBanJiPaiKeXinXi(cmd);
        return PPResult.Ok();
    }

    /**
     * 创建学员
     * @param dtoXueYuan
     * @return
     */
    @PostMapping("chuangJianXueYuan")
    public PPResult chuangJianXueYuan(@Valid @RequestBody DtoXueYuan dtoXueYuan) {
        Long id = combineService.chuangJianXueYuan(dtoXueYuan);
        Map<String, Object> res = new HashMap<>();
        res.put("id", id);
        return PPResult.getPPResultOK(res);
    }

    /**
     * 学员报名
     * @param dtoXueYuanBaoMing
     * @return
     */
    @PostMapping("xueYuanBaoMing")
    public PPResult xueYuanBaoMing(@Valid @RequestBody DtoXueYuanBaoMing dtoXueYuanBaoMing) {
        combineService.xueYuanBaoMing(dtoXueYuanBaoMing);
        return PPResult.Ok();
    }

    /**
     * 学员（学员课程）指定班级
     * @param xueYuanId 学员Id
     * @param xueYuanKeChengId 学员课程id
     * @param banJiId 班级Id
     */
    @GetMapping("xueYuanXuanBan")
    public PPResult xueYuanXuanBan(@RequestParam Long xueYuanId, @RequestParam Long xueYuanKeChengId, @RequestParam Long banJiId) {
        combineService.xueYuanXuanBan(xueYuanId, xueYuanKeChengId, banJiId);
        return PPResult.Ok();
    }

    /**
     * 缴费记录确认
     * @param id 缴费记录Id
     * @param jiaoFeiJiLuZhuangTai 缴费记录状态
     * @return
     */
    @GetMapping("jiaoFeiJiLuQueRen")
    public PPResult jiaoFeiJiLuQueRen(@RequestParam Long id, @RequestParam JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai) {
        combineService.jiaoFeiJiLuQueRen(id, jiaoFeiJiLuZhuangTai);
        return PPResult.Ok();
    }

    /**
     * 排课记录点名
     * @param dtoPaiKeJiLu 排课记录点名信息
     * @return
     */
    @PostMapping("paiKeJiLuDianMing")
    public PPResult paiKeJiLuDianMing(@RequestBody DtoPaiKeJiLu dtoPaiKeJiLu) {
        combineService.paiKeJiLuDianMing(dtoPaiKeJiLu);
        return PPResult.Ok();
    }

    /**
     * 导出班级排课记录
     * @param shangKeRiQiBegin 上课日期 开始
     * @param shangKeRiQiEnd 上课日期 结束
     * @param banJiId 班级Id
     * @param shangKeLaoShiId 上课老师Id
     * @param paiKeJiLuZhuangTaiZu 排课记录状态列表（[待点名 | 已点名 | 已点评]）
     * @return
     */
    @GetMapping("daoChuBanJiPaiKeJiLu")
    public PPResult daoChuBanJiPaiKeJiLu(@RequestParam(required = false) Long shangKeRiQiBegin,
                                         @RequestParam(required = false) Long shangKeRiQiEnd,
                                         @RequestParam(required = false) Long banJiId,
                                         @RequestParam(required = false) Long shangKeLaoShiId,
                                         @RequestParam(required = false) List<PaiKeJiLuZhuangTai> paiKeJiLuZhuangTaiZu) {
        DtoPaiKeJiLuQuery dtoPaiKeJiLuQuery = new DtoPaiKeJiLuQuery();

        dtoPaiKeJiLuQuery.setShangKeRiQiBegin(shangKeRiQiBegin);
        dtoPaiKeJiLuQuery.setShangKeRiQiEnd(shangKeRiQiEnd);
        dtoPaiKeJiLuQuery.setBanJiId(banJiId);
        dtoPaiKeJiLuQuery.setShangKeLaoShiId(shangKeLaoShiId);
        dtoPaiKeJiLuQuery.setPaiKeJiLuZhuangTaiZu(paiKeJiLuZhuangTaiZu);

        combineService.daoChuPaiKeJiLu(dtoPaiKeJiLuQuery);
        return PPResult.Ok();
    }


    /**
     * 导出学员点名记录
     * @param xueYuanId 学员Id
     * @param shangKeRiQiBegin
     * @param shangKeRiQiEnd
     * @param banJiId
     * @param shangKeLaoShiId
     * @return
     */
    @GetMapping("daoChuXueYuanDianMingJiLu")
    public PPResult daoChuXueYuanDianMingJiLu(@RequestParam(required = false) Long xueYuanId,
                                             @RequestParam(required = false) Long shangKeRiQiBegin,
                                             @RequestParam(required = false) Long shangKeRiQiEnd,
                                             @RequestParam(required = false) Long banJiId,
                                             @RequestParam(required = false) Long shangKeLaoShiId
    ) {
        DtoDianMingJiLuQuery dianMingJiLuQuery = new DtoDianMingJiLuQuery();

        dianMingJiLuQuery.setXueYuanId(xueYuanId);
        dianMingJiLuQuery.setShangKeRiQiBegin(shangKeRiQiBegin);
        dianMingJiLuQuery.setShangKeRiQiEnd(shangKeRiQiEnd);
        dianMingJiLuQuery.setBanJiId(banJiId);
        dianMingJiLuQuery.setShangKeLaoShiId(shangKeLaoShiId);

        combineService.daoChuXueYuanDianMingJiLu(dianMingJiLuQuery);

        return PPResult.Ok();
    }
}
