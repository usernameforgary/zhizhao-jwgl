package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.repository.PaiKeJiLuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaiKeJiLuServiceImp implements PaiKeJiLuService{
    @Autowired
    PaiKeJiLuRepository paiKeJiLuRepository;

    @Transactional
    @Override
    public void saveAllPaiKeJiLu(List<PaiKeJiLu> paiKeJiLuList) {
        paiKeJiLuRepository.saveAll(paiKeJiLuList);
    }
}
