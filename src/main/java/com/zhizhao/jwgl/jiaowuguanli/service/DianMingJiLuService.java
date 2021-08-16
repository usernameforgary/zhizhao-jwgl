package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.dianmingjilu.DianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLuQuery;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;

import javax.validation.Valid;

public interface DianMingJiLuService {
     /**
      * 创建点名记录
      * @param chuangJianCmd
      * @return
      */
     DianMingJiLu chuangJian(@Valid DianMingJiLu.ChuangJianCmd chuangJianCmd);

     /**
      * 分页获取点名记录
      * @param dtoDianMingJiLuQuery
      * @return
      */
     DtoPageResult<DtoDianMingJiLu> huoQuDianMingJiLu(DtoDianMingJiLuQuery dtoDianMingJiLuQuery);
}
