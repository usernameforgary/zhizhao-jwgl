package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;

public interface LaoShiService {
    Long chuangJianLaoShi(LaoShi.ChuangJianCmd cmd);

    /**
     * 获取老师列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字【老师姓名 | 手机号】
     * @param zaiZhiZhuangTai 在职状态
     * @return
     */
    DtoPageResult<DtoLaoShi> huoQuLaoShiLieBiao(Integer pageNum, Integer pageSize, String keyword, Boolean zaiZhiZhuangTai);
}
