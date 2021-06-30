package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.repository.LaoShiRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LaoShiServiceImp implements LaoShiService {
    @Autowired
    LaoShiRepository laoShiRepository;

    @Override
    @Transactional
    public Long chuangJianLaoShi(LaoShi.ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            cmd.setId(SnowflakeIdUtil.nextId());
        }

        LaoShi laoShi = LaoShi.chuangJian(cmd);
        laoShiRepository.save(laoShi);
        return laoShi.getId();
    }
}
