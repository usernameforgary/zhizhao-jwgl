package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XiTongCaiDanMapper;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("xiTongCaiDan")
public class XiTongCaiDanController {
    @Resource
    XiTongCaiDanMapper xiTongCaiDanMapper;

    @GetMapping("huoQuXiTongCaiDan")
    public PPResult huoQuXiTongCaiDan() {
        List<XiTongCaiDan> xiTongCaiDans = xiTongCaiDanMapper.selectAll();
        return PPResult.getPPResultOK(xiTongCaiDans);
    }
}
