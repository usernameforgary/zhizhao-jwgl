package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.GenJinZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;
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
     * 分页获取学员列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("huoQuXueYuanLieBiao")
    public PPResult huoQuXueYuanLieBiao(@RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        DtoPageResult<DtoXueYuan> dtoPageResult = xueYuanService.huoQuXueYuanLieBiao(pageNum, pageSize);
        return PPResult.getPPResultOK(dtoPageResult);
    }

    /**
     * 分页获取学员列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("huoQuXueYuanLieBiaoV2")
    public PPResult huoQuXueYuanLieBiaoV2(@RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        DtoPageResult<DtoXueYuan> dtoPageResult = xueYuanService.huoQuXueYuanLieBiaoV2(pageNum, pageSize);
        return PPResult.getPPResultOK(dtoPageResult);
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

    /**
     * 获取潜在学员列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字
     * @param genJinZhuangTai 跟进状态
     * @param genJinRenId 跟进人
     * @return
     */
    @GetMapping("huoQuQianZaiXueYuanLieBiao")
    public PPResult huoQuQianZaiXueYuanLieBiao(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String genJinZhuangTai,
            @RequestParam(required = false) Long genJinRenId
            ) {
        DtoPageResult<DtoXueYuanQianZai> dtoPageResult = xueYuanService.huoQuQianZaiXueYuanLieBiao(pageNum, pageSize, keyword,  genJinZhuangTai, genJinRenId);
        return PPResult.getPPResultOK(dtoPageResult);
    }

    /**
     * 获取在读学员列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字
     * @param banJiId 班级Id
     * @return
     */
    @GetMapping("huoQuZaiDuXueYuanLieBiao")
    public PPResult huoQuZaiDuXueYuanLieBiao(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String banJiId
    ) {
        DtoPageResult<DtoXueYuanZaiDu> dtoPageResult = xueYuanService.huoQuZaiDuXueYuanLieBiao(pageNum, pageSize, keyword,  banJiId);
        return PPResult.getPPResultOK(dtoPageResult);
    }

    /**
     * 获取历史学员列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字
     * @param keChengId 课程Id
     * @param genJinRenId 跟进人Id
     * @return
     */
    @GetMapping("huoQuLiShiXueYuanLieBiao")
    public PPResult huoQuLiShiXueYuanLieBiao(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String keChengId,
            @RequestParam(required = false) String genJinRenId
    ) {
        DtoPageResult<DtoXuYuanLiShi> dtoPageResult = xueYuanService.huoQuLiShiXueYuanLieBiao(pageNum, pageSize, keyword,  keChengId, genJinRenId);
        return PPResult.getPPResultOK(dtoPageResult);
    }
}
