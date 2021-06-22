package com.zhizhao.jwgl.jiaowuguanli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xiTong")
public class SystemController {
    @GetMapping("huoQuXiTongCaiDan")
    public String huoQuXiTongCaiDan() {
        return "hello";
    }
}
