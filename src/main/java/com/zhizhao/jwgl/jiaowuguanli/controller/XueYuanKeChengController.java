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

    @GetMapping("getXueYuanKeChengByXueYuanId")
    public PPResult getXueYuanKeChengByXueYuanId(@RequestParam Long xueYuanId) {
        List<DtoXueYuanKeCheng> dtoXueYuanKeChengList = xueYuanKeChengService.getXueYuanKeChengByXueYuanId(xueYuanId);
        return PPResult.getPPResultOK(dtoXueYuanKeChengList);
    }
}
