package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
public class MainController {
    // 创建系统菜单
    @PostMapping("chuangJianXiTongCaiDan")
    public PPResult chuangJianXiTongCaiDan() {
        return PPResult.Ok();
    }
}
