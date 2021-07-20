package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.KeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.mapper.KeChengMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeChengServiceImp implements KeChengService{
    @Resource
    KeChengMapper keChengMapper;

    @Override
    public IPage<DtoKeCheng> huoQuKeChengLieBiao(Integer pageNum, Integer pageSize) {
        Page<KeCheng> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<KeCheng> keChengLambdaQueryWrapper = Wrappers.lambdaQuery();
        keChengLambdaQueryWrapper.eq(KeCheng::getIsDeleted, false);

        IPage<DtoKeCheng> result = keChengMapper.getKeChengLieBiao(page, keChengLambdaQueryWrapper);
        return result;
    }

    /**
     * 根据学员Id获取当前学员未报名的课程，如果学员Id为空，则返回所有课程
     * @param xueYuanId
     * @return
     */
    @Override
    public List<KeCheng> getWeiXuanKeChengListByXueYuanId(Long xueYuanId) {
        List<KeCheng> keChengList = keChengMapper.getWeiXuanKeChengListByXueYuanId(xueYuanId);
        return keChengList;
    }
}
