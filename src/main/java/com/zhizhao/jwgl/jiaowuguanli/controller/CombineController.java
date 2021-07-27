package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanBaoMing;
import com.zhizhao.jwgl.jiaowuguanli.service.CombineService;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
     * @param previousBanJiId 原来班级Id
     */
    @GetMapping("xueYuanXuanBan")
    public PPResult xueYuanXuanBan(@RequestParam Long xueYuanId, @RequestParam Long xueYuanKeChengId, @RequestParam Long banJiId, @RequestParam(required = false) Long previousBanJiId) {
        combineService.xueYuanXuanBan(xueYuanId, xueYuanKeChengId, banJiId, previousBanJiId);
        return PPResult.Ok();
    }
}
