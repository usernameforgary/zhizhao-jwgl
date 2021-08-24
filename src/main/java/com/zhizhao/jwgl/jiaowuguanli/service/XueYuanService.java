package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanQianZai;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface XueYuanService {
    /**
     * 创建学员
     * @param cmd
     * @return
     */
    Long chuangJianXueYuan(XueYuan.ChuangJianCmd cmd);

    /**
     * 根据学员Id获取学员信息
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
     * 分页获取学员列表
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @return
     */
    DtoPageResult<DtoXueYuan> huoQuXueYuanLieBiao(Integer pageNum, Integer pageSize);

    /**
     * 分页获取学员列表V2
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @return
     */
    DtoPageResult<DtoXueYuan> huoQuXueYuanLieBiaoV2(Integer pageNum, Integer pageSize);

    /**
     * 根据姓名和所属账号手机号获取学员
     * @param xingMing 学员
     * @param shouJi 账号手机号
     * @return
     */
    DtoXueYuan huoQuXueYuanByXinMingAndZhangHaoShouJi(String xingMing, String shouJi);

    /**
     * 更新学员
     * @param xueYuan
     */
    void gengXinXueYuan(XueYuan xueYuan);

    /**
     * 根据Id获取学员
     * @param id 学员id
     * @return
     */
    Optional<XueYuan> huoQuXueYuanById(Long id);

    /**
     * 获取潜在学员列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字
     * @param genJinZhuangTai 跟进状态
     * @param genJinRenId 跟进人
     * @return
     */
    DtoPageResult<DtoXueYuanQianZai> huoQuQianZaiXueYuanLieBiao(Integer pageNum, Integer pageSize, String keyword, String genJinZhuangTai, Long genJinRenId);
}
