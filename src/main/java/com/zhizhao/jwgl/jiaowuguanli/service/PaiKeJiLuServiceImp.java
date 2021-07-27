package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.DateUtil;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.repository.PaiKeJiLuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    /**
     * 根据排课信息Id，获取所有（未删除，未排课，未点名，上课日期晚于给定日期）的排课记录
     * @param paiKeXinXiIdList 排课信息Id列表
     * @return
     */
    @Override
    public List<PaiKeJiLu> getAllByPaiKeXinXinId(List<Long> paiKeXinXiIdList) {
        Date now = new Date();
        // 当前日期，往前取一天
        now = DateUtil.offsetDay(DateUtil.date(), -1);
        long beginTime = DateUtil.endOfDay(now).getTime();
        List <PaiKeJiLu> paiKeJiLuList = paiKeJiLuRepository.findAllByBanJiPaiKeXinXiIdInAndPaiKeJiLuZhuangTaiEqualsAndShangKeRiQiAfterAndIsDeletedFalse(paiKeXinXiIdList, PaiKeJiLuZhuangTai.DAI_DIAN_MING, beginTime);
        return paiKeJiLuList;
    }
}
