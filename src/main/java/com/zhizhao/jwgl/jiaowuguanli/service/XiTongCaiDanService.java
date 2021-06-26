package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XiTongCaiDanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XiTongCaiDanService {
    @Resource
    XiTongCaiDanMapper xiTongCaiDanMapper;

    public void houQuXiTongCaiDan() {
        List<XiTongCaiDan> xiTongCaiDans = xiTongCaiDanMapper.selectAll();
    }
}
