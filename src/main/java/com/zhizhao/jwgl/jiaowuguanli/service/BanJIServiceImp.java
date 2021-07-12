package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.BanJiZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BanJiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BanJIServiceImp implements BanJiService{
    @Resource
    BanJiMapper banJiMapper;

    @Override
    public IPage<DtoBanJi> huoQuBanJiLieBiao(Integer pageNum, Integer pageSize) {
        Page<BanJi> page = new Page<>(pageNum, pageSize);

        return  banJiMapper.huoQuBanJiLeiBiao(page);
    }

    @Override
    public DtoBanJi huoQuBanJiXiangQing(Long id) {
        if(id == null) {
            throw new BusinessException("请提供需要查询的班级");
        }
        return banJiMapper.huoQuBanJiXiangQing(id);
    }
}