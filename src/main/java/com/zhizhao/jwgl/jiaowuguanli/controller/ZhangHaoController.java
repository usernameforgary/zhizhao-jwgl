package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ZhangHaoMapper;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("zhanghao")
public class ZhangHaoController {
    @Resource
    ZhangHaoMapper zhangHaoMapper;

    @GetMapping("yuanGongLieBiao")
    public PPResult<Page<ZhangHao>> huoQuYuanGongLiebiao(
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
       Page page = new Page(pageNum, pageSize);

       return PPResult.getPPResultOK(zhangHaoMapper.yongHuLieBiao(page));
    }
}
