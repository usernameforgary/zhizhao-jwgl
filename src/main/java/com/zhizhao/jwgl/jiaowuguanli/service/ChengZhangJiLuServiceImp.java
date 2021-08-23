package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjilu.ChengZhangJiLu;
import com.zhizhao.jwgl.jiaowuguanli.repository.ChengZhangJiLuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChengZhangJiLuServiceImp implements ChengZhangJiLuService{
    @Autowired
    ChengZhangJiLuRepository chengZhangJiLuRepository;
    /**
     * 创建成长记录
     *
     * @param cmd
     * @return
     */
    @Transactional
    @Override
    public ChengZhangJiLu chuangJian(ChengZhangJiLu.ChuangJianCmd cmd) {
        ChengZhangJiLu chengZhangJiLu = ChengZhangJiLu.chuangJian(cmd);
        chengZhangJiLuRepository.save(chengZhangJiLu);
        return chengZhangJiLu;
    }
}
