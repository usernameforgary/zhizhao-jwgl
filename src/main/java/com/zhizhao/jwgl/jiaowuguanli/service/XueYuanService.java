package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;

import java.util.List;

public interface XueYuanService {
    /**
     * 创建学员
     * @param cmd
     * @return
     */
    Long chuangJianXueYuan(XueYuan.ChuangJianCmd cmd);
    /**
     * 获取学员信息
     * @param xueYuanId
     * @return
     */
    DtoXueYuan huoQuXueYuanXinXi(Long xueYuanId);

    /**
     * 获取所有学员
     * @return
     */
    List<DtoXueYuan> huoQuXueYuanAll();

    /**
     * 根据姓名和所属账号手机号获取学员
     * @param xingMing 学员
     * @param shouJi 账号手机号
     * @return
     */
    DtoXueYuan huoQuXueYuanByXinMingAndZhangHaoShouJi(String xingMing, String shouJi);
}
