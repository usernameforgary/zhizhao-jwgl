package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;
import com.zhizhao.jwgl.jiaowuguanli.mapper.LaoShiMapper;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("laoshi")
public class LaoShiController {
    @Resource
    LaoShiMapper laoShiMapper;

    /**
     * 获取所有老师
     * @return
     */
    @GetMapping("huoQuLaoShiAll")
    public PPResult huoQuLaoShiAll() {
        List<DtoLaoShi> laoShiList = laoShiMapper.huoQuLaoShiAll();
        return PPResult.getPPResultOK(laoShiList);
    }
}
