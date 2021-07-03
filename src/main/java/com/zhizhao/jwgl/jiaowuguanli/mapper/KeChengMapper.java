package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.KeCheng;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;
import org.apache.ibatis.annotations.Param;

public interface KeChengMapper extends MyBaseMapper<KeCheng>{
    IPage<DtoKeCheng> getKeChengLieBiao(IPage page, @Param("ew") Wrapper<KeCheng> wrapper);
}
