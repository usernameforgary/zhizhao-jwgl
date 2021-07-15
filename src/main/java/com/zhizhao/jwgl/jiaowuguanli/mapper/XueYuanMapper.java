package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;

import java.util.List;

public interface XueYuanMapper extends MyBaseMapper<XueYuan> {
    DtoXueYuan huoQuXueYuanXinXi(Long xueYuanId);
    List<DtoXueYuan> huoQuXueYuanAll();

    /**
     * 根据姓名和所属账号手机号获取学员
     * @param xingMing 学员
     * @param shouJi 账号手机号
     * @return
     */
    DtoXueYuan huoQuXueYuanByXinMingAndZhangHaoShouJi(String xingMing, String shouJi);
}
