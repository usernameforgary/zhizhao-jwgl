package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanQianZai;

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

    /**
     * 分页获取学员列表
      * @param page mybatic plus分页信息
     * @return
     */
    IPage<DtoXueYuan> huoQuXueYuanLieBiao(IPage page);

    /**
     * 分页获取学员列表 verison 2
     * @param page mybatic plus分页信息
     * @return
     */
    IPage<DtoXueYuan> huoQuXueYuanLieBiaoV2(IPage page);

    /**
     * 获取潜在学员列表
     *
     * @param page mybatic plus分页信息
     * @param keyword         关键字
     * @param genJinZhuangTai 跟进状态
     * @param genJinRenId     跟进人
     * @return
     */
    IPage<DtoXueYuanQianZai> huoQuQianZaiXueYuanLieBiao(IPage page, String keyword, String genJinZhuangTai, Long genJinRenId);
}
