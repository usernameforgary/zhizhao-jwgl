package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoJiaoFeiJiLu;

/**
 * 缴费记录
 */
public interface JiaoFeiJiLuService {
    /**
     * 创建缴费记录
     * @param cmd
     * @return
     */
    Long chuangJian(JiaoFeiJiLu.ChuangJianCmd cmd);

    /**
     * 获取缴费记录列表
     * @param page
     * @param pageSize
     * @return
     */
    IPage<DtoJiaoFeiJiLu> huoQuJiaoFeiJiLuLieBiao(Integer page, Integer pageSize);

    /**
     * 根据Id获取缴费记录
     * @param id 缴费记录Id
     * @return
     */
    JiaoFeiJiLu getById(Long id);

    /**
     * 更改缴费记录状态
     * @param id 缴费记录Id
     * @param jiaoFeiJiLuZhuangTai 缴费记录状态
     */
    void gengGaiJiaoFenJiLuZhuangTai(Long id, JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai);
}
