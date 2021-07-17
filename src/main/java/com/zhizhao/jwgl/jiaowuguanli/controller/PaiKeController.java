package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.service.BanJiPaiKeXinXiService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("paike")
public class PaiKeController {
    @Autowired
    BanJiPaiKeXinXiService banJiPaiKeXinXiService;

    /**
     * 获取班级排课信息
     * @param banJiId
     * @return
     */
    @GetMapping("huoquBanJiPaiKeXinXi")
    public PPResult huoQuBanJiPaiKeXinXiLieBiao(@RequestParam Long banJiId) {
        List<DtoBanJiPaiKeXinXi> banJiPaiKeXinXiList = banJiPaiKeXinXiService.huoQuBanJiPaiKeXinXi(banJiId);
        return PPResult.getPPResultOK(banJiPaiKeXinXiList);
    }
}
