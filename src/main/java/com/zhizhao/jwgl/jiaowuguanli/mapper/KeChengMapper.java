package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.KeCheng;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeChengMapper extends MyBaseMapper<KeCheng>{
    IPage<DtoKeCheng> getKeChengLieBiao(IPage page, @Param("ew") Wrapper<KeCheng> wrapper);

    /**
     * 根据学员Id获取当前学员未报名的课程，如果学员Id为空，则返回所有课程
     * @param xueYuanId
     * @return
     */
    List<KeCheng> getWeiXuanKeChengListByXueYuanId(Long xueYuanId);
}
