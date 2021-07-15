package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;

import java.util.List;

public interface ZhangHaoService {
    Long chuangJianZhangHao(ZhangHao.ChuangJianCmd cmd);
    /**
     * 根据手机和账号类型获取账号信息
     * @param shouJi
     * @param zhangHaoLeiXing
     * @return
     */
    ZhangHao getZhangHaoByShouJiAndLeiXing(Long shouJi, ZhangHaoLeiXing zhangHaoLeiXing);
}
