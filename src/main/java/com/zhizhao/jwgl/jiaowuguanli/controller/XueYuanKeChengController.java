package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.service.XueYuanKeChengService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("xueyuankecheng")
@ResponseBody
public class XueYuanKeChengController {
    @Autowired
    XueYuanKeChengService xueYuanKeChengService;

    /**
     *
     * @param xueYuanId 学员id
     * @param isLiShi 是否是历史课程（已结课的课程，XueYuanKeChengZhuangTai.YI_JIE_KE）
     * @return
     */
    @GetMapping("getXueYuanKeChengByXueYuanId")
    public PPResult getXueYuanKeChengByXueYuanId(@RequestParam Long xueYuanId, @RequestParam(required = false) boolean isLiShi) {
        List<DtoXueYuanKeCheng> dtoXueYuanKeChengList = xueYuanKeChengService.getXueYuanKeChengByXueYuanId(xueYuanId, isLiShi);
        return PPResult.getPPResultOK(dtoXueYuanKeChengList);
    }
}
