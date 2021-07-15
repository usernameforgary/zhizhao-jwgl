package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.service.CombineService;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
