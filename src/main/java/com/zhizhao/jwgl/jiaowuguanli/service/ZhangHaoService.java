package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;

import java.util.List;

public interface ZhangHaoService {
    Long chuangJianZhangHao(ZhangHao.ChuangJianCmd cmd);
}
