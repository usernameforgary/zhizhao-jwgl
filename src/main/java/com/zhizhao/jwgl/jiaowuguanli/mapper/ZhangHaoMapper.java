package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;

public interface ZhangHaoMapper extends MyBaseMapper<ZhangHao> {
    IPage<ZhangHao> yongHuLieBiao(IPage page);
}
