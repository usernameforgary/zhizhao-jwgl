package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoJiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.service.JiaoFeiJiLuService;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("jiaofeijilu")
public class JiaoFeiJiLuController {
    @Autowired
    JiaoFeiJiLuService jiaoFeiJiLuService;

    @GetMapping("huoQuJiaoFeiJiLuLieBiao")
    public PPResult huoQuJiaoFeiJiLuLieBiao(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        IPage<DtoJiaoFeiJiLu> pageResult = jiaoFeiJiLuService.huoQuJiaoFeiJiLuLieBiao(pageNum, pageSize);

        Map<String, Object> result = Converter.convertMyBatisPlusPageResult(pageResult);
        return PPResult.getPPResultOK(result);
    }
}
