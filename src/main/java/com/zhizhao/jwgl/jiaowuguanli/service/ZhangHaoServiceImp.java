package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ZhangHaoServiceImp implements ZhangHaoService {
    @Value("${jiaowuguanli.security.psw.default}")
    private String defaultPsw;

    @Autowired
    ZhangHaoRepository zhangHaoRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Long chuangJianZhangHao(ZhangHao.ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            cmd.setId(SnowflakeIdUtil.nextId());
        }

        ZhangHao zhangHao = ZhangHao.chuangJian(cmd);
        // 设置默认密码
        if(zhangHao.getMiMa() == null) {
            zhangHao.setMiMa(bCryptPasswordEncoder.encode(defaultPsw));
        }

        zhangHao.setIsDeleted(false);

        zhangHaoRepository.save(zhangHao);
        return zhangHao.getId();
    }
}
