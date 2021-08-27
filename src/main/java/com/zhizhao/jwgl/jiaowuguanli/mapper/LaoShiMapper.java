package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;

import java.util.List;

public interface LaoShiMapper extends MyBaseMapper<LaoShi> {
    List<DtoLaoShi> huoQuLaoShiAll();

    /**
     * 获取老师列表
     * @param page
     * @param keyword 关键字【老师姓名 | 手机号】
     * @param keyword 关键字【老师姓名 | 手机号】
     * @param zaiZhiZhuangTai 在职状态
     * @return
     */
    IPage<DtoLaoShi> huoQuLaoShiLieBiao(Page<DtoLaoShi> page, String keyword, Boolean zaiZhiZhuangTai);
}
