package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjiluwenjian.ChengZhangJiLuWenJian;
import com.zhizhao.jwgl.jiaowuguanli.repository.ChengZhangJiLuWenJianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChengZhangJiLuWenJianServiceImp implements ChengZhangJiLuWenJianService{
    @Autowired
    ChengZhangJiLuWenJianRepository chengZhangJiLuWenJianRepository;
    /**
     * 创建成长记录
     *
     * @param chuangJianCmd
     * @return
     */
    @Transactional
    @Override
    public ChengZhangJiLuWenJian chuangJian(ChengZhangJiLuWenJian.ChuangJianCmd chuangJianCmd) {
        ChengZhangJiLuWenJian chengZhangJiLuWenJian = ChengZhangJiLuWenJian.chuangJian(chuangJianCmd);
        chengZhangJiLuWenJianRepository.save(chengZhangJiLuWenJian);
        return chengZhangJiLuWenJian;
    }
}
