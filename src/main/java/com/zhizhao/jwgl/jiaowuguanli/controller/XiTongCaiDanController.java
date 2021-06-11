package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDanView;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XiTongCaiDanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("xitongcaidan")
public class XiTongCaiDanController {

    @Autowired
    private XiTongCaiDanMapper xiTongCaiDanMapper;

    @GetMapping("/getAll")
    public List<XiTongCaiDanView> getAll() {
        return xiTongCaiDanMapper.selectAll();
    }
}
