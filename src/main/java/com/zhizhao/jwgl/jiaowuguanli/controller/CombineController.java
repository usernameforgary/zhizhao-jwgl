package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("combine")
public class CombineController {

    @Autowired
    YuanGongService yuanGongService;

    @PostMapping("tianjiaYuanGong")
    public PPResult tainJiaYuanGong(@Valid @RequestBody DtoTianJiaYuanGong dto) {
        yuanGongService.tianJianYuanGong(dto);
        return PPResult.Ok();
    }
}
