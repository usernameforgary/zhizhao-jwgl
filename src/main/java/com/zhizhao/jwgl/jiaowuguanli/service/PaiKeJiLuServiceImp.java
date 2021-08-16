package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLuQuery;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.mapper.PaiKeJiLuMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.PaiKeJiLuRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PaiKeJiLuServiceImp implements PaiKeJiLuService{
    @Autowired
    PaiKeJiLuRepository paiKeJiLuRepository;

    @Resource
    PaiKeJiLuMapper paiKeJiLuMapper;

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

    /**
     * 分页获取排课记录
     * @param dtoPaiKeJiLuQuery 查询参数Dto
     * @return
     */
    @Override
    public DtoPageResult<DtoPaiKeJiLu> getPaiKeJiLuList(DtoPaiKeJiLuQuery dtoPaiKeJiLuQuery) {
        Page<DtoPaiKeJiLu> page = new Page<>(dtoPaiKeJiLuQuery.getPageNum(), dtoPaiKeJiLuQuery.getPageSize());
        String paiKeJiLuZhuangTaiStr = null;
        if(dtoPaiKeJiLuQuery.getPaiKeJiLuZhuangTai() != null) {
            paiKeJiLuZhuangTaiStr = dtoPaiKeJiLuQuery.getPaiKeJiLuZhuangTai().toString();
        }
        IPage<DtoPaiKeJiLu> paiKeJiLuPageResult = paiKeJiLuMapper.getPaiKeJiLuList(
                page,
                paiKeJiLuZhuangTaiStr,
                dtoPaiKeJiLuQuery.getShangKeRiQiBegin(),
                dtoPaiKeJiLuQuery.getShangKeRiQiEnd(),
                dtoPaiKeJiLuQuery.getBanJiId(),
                dtoPaiKeJiLuQuery.getShangKeLaoShiId());

        DtoPageResult<DtoPaiKeJiLu> dtoPageResult = Converter.convertPageResultMybatisPlus(paiKeJiLuPageResult);

        return dtoPageResult;
    }

    /**
     * 根据Id获取排课记录
     *
     * @param id 排课记录Id
     * @return
     */
    @Override
    public PaiKeJiLu getPaiKeJiLuById(Long id) {
        PaiKeJiLu paiKeJiLu = paiKeJiLuRepository.getById(id);
        return paiKeJiLu;
    }
}
