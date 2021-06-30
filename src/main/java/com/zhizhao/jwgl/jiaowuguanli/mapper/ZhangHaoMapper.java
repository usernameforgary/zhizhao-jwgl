package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.vo.ZhangHaoVo;
import org.apache.ibatis.annotations.Param;

public interface ZhangHaoMapper extends MyBaseMapper<ZhangHao> {
    IPage<ZhangHaoVo> yuanGongLieBiao(IPage page, @Param("ew") Wrapper<ZhangHao> wrapper);
}
