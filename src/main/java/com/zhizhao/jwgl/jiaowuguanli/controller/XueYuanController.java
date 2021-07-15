package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.service.XueYuanService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("xueyuan")
public class XueYuanController {
    @Autowired
    XueYuanService xueYuanService;

    /**
     * 根据学员Id获取学员信息
     * @param xueYuanId
     * @return
     */
    @GetMapping("huoQuXueYuanXinXi")
    public PPResult huoQuXueYaunXinXi(@RequestParam Long xueYuanId) {
        DtoXueYuan dtoXueYuan = xueYuanService.huoQuXueYuanXinXi(xueYuanId);
        return PPResult.getPPResultOK(dtoXueYuan);
    }

    /**
     * 获取所有学员
      * @return
     */
    @GetMapping("huoQuXueYuanAll")
    public PPResult huoQuXueYuanAll() {
        List<DtoXueYuan> xueYuanList = xueYuanService.huoQuXueYuanAll();
        return PPResult.getPPResultOK(xueYuanList);
    }

    /**
     * 根据姓名和所属账号手机号获取学员
     * @param xingMing 学员
     * @param shouJi 账号手机号
     * @return
     */
    @GetMapping("huoQuXueYuanByXingMingAndZhangHaoShouJi")
    public PPResult huoQuXueYuanByXinMingAndZhangHaoShouJi(@RequestParam String xingMing, @RequestParam String shouJi) {
        DtoXueYuan dtoXueYuan = xueYuanService.huoQuXueYuanByXinMingAndZhangHaoShouJi(xingMing, shouJi);
        return PPResult.getPPResultOK(dtoXueYuan);
    }
}
