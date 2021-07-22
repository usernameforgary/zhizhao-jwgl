package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;

import java.util.List;

public interface PaiKeJiLuService {
    /**
     * 保存所有排课记录
     * @param paiKeJiLuList
     */
    void saveAllPaiKeJiLu(List<PaiKeJiLu> paiKeJiLuList);
}
