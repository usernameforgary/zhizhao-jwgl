package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.biaoqian.BiaoQian;
import com.zhizhao.jwgl.jiaowuguanli.domain.shanchangkemu.ShanChangKeMu;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BiaoQianMapper;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ShanChangKeMuMapper;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("common")
public class CommonController {
    @Resource
    ShanChangKeMuMapper shanChangKeMuMapper;

    @Resource
    BiaoQianMapper biaoQianMapper;

    @GetMapping("huoQuShanChangKeMuLieBiao")
    public PPResult huoQuShanChangKeMuLieBiao() {
        List<ShanChangKeMu> shanChangKeMus = shanChangKeMuMapper.selectAll();
        return PPResult.getPPResultOK(shanChangKeMus);
    }

    @GetMapping("huoQuBiaoQianLieBiao")
    public PPResult huoQuBiaoQianLieBiao() {
        List<BiaoQian> biaoQians = biaoQianMapper.selectAll();
        return PPResult.getPPResultOK(biaoQians);
    }
}
