package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.bukejilu.BuKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.repository.BuKeJiLuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BuKeJiLuServiceImp implements BuKeJiLuService{
    @Autowired
    BuKeJiLuRepository buKeJiLuRepository;
    /**
     * 创建补课记录
     *
     * @param cmd 补课记录创建cmd
     * @return
     */
    @Transactional
    @Override
    public BuKeJiLu chuangJian(BuKeJiLu.ChuangJianCmd cmd) {
        BuKeJiLu buKeJiLu = BuKeJiLu.chuangJian(cmd);
        buKeJiLuRepository.save(buKeJiLu);
        return buKeJiLu;
    }
}
