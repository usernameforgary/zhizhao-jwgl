package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class YuanGongServiceImp implements YuanGongService {
    @Autowired
    ZhangHaoRepository zhangHaoRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void tianJianYuanGong(DtoTianJiaYuanGong dto) {
        //TODO not finished
        ZhangHao zhangHao = new ZhangHao();
        zhangHao.setId(dto.getId());
        zhangHao.setShouJi(dto.getShouJi());
        zhangHao.setXingMing(dto.getXingMing());
        zhangHao.setMiMa(bCryptPasswordEncoder.encode("123456"));
        zhangHao.setZhangHaoLeiXing(dto.getZhangHaoLeiXing());
        zhangHaoRepository.save(zhangHao);
    }
}
