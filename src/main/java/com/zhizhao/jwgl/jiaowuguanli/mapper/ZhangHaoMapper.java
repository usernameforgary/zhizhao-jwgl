package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.vo.ZhangHaoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZhangHaoMapper extends MyBaseMapper<ZhangHao> {
    IPage<ZhangHaoVo> yuanGongLieBiao(IPage page, @Param("ew") Wrapper<ZhangHao> wrapper);

    /**
     * 根据手机和账号类型获取账号信息
     * @param shouJi
     * @param zhangHaoLeiXing
     * @return
     */
    ZhangHao getZhangHaoByShouJiAndLeiXing(String shouJi, ZhangHaoLeiXing zhangHaoLeiXing);

    /**
     * 根据账号类型获取系统账号列表(基础信息）
     * @param zhangHaoLeiXing
     * @return
     */
    List<ZhangHao> getZhangHaoByLeiXing(ZhangHaoLeiXing zhangHaoLeiXing);

    /**
     * 获取所有员工
     * @return
     */
    List<DtoZhangHao> huoQuYuanGongAll();
}
