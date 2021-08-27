package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.PaiKeJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.paike.PaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.PaiKeJiLuMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.PaiKeJiLuRepository;
import com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun.OSSHelper;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PaiKeJiLuServiceImp implements PaiKeJiLuService{
    @Autowired
    PaiKeJiLuRepository paiKeJiLuRepository;

    @Resource
    PaiKeJiLuMapper paiKeJiLuMapper;

    @Autowired
    OSSHelper ossHelper;

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
        List<String> paiKeJiLuZhuangTaiZu = new ArrayList<>();
        if(dtoPaiKeJiLuQuery.getPaiKeJiLuZhuangTaiZu() != null && dtoPaiKeJiLuQuery.getPaiKeJiLuZhuangTaiZu().size() > 0) {
            for(PaiKeJiLuZhuangTai paiKeJiLuZhuangTai : dtoPaiKeJiLuQuery.getPaiKeJiLuZhuangTaiZu()) {
                String paiKeJiLuZhuangTaiStr = paiKeJiLuZhuangTai.toString();
                paiKeJiLuZhuangTaiZu.add(paiKeJiLuZhuangTaiStr);
            }
        }
        IPage<DtoPaiKeJiLu> paiKeJiLuPageResult = paiKeJiLuMapper.getPaiKeJiLuList(
                page,
                paiKeJiLuZhuangTaiZu,
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

    /**
     * 根据排课记录Id，查询排课记录，课后点评
     *
     * @param id
     * @return
     */
    @Override
    public DtoPaiKeJiLu getPaiKeJiLuKeHouDianPingById(Long id) {
        if(id == null) {
            throw new BusinessException("请提供要查询的排课记录");
        }
        DtoPaiKeJiLu dtoPaiKeJiLu = paiKeJiLuMapper.getPaiKeJiLuKeHouDianPingById(id);
        // 处理课后点评文件组，添加url
        Set<DtoChengZhangJiLu> dtoChengZhangJiLuSet = dtoPaiKeJiLu.getChengZhangJiLuZu();
        String ossPublicDomain = ossHelper.getOssProperties().getBucketPublicDomain();
        for(DtoChengZhangJiLu dtoChengZhangJiLu: dtoChengZhangJiLuSet) {
            Set<DtoChengZhangJiLuWenJian> dtoChengZhangJiLuWenJianSet = dtoChengZhangJiLu.getChengZhangJiLuWenJianZu();
            for(DtoChengZhangJiLuWenJian dtoChengZhangJiLuWenJian: dtoChengZhangJiLuWenJianSet) {
                dtoChengZhangJiLuWenJian.setUrl(ossPublicDomain);
            }
        }
        return dtoPaiKeJiLu;
    }

    /**
     * 根据排课信息Id，获取所有未点名的排课记录
     * @param paiKeXinXinXiIdList 排课信息Id组
     * @return
     */
    @Override
    public List<PaiKeJiLu> getWeiDianMingPaiKeJiLuByPaiKeXinXiId(List<Long> paiKeXinXinXiIdList) {
        if(paiKeXinXinXiIdList == null || paiKeXinXinXiIdList.size() == 0) {
            throw new BusinessException("请提供要查询的排课信息");
        }
        List<PaiKeJiLu> paiKeJiLuList = paiKeJiLuRepository.getWeiDianMingPaiKeJiLuByPaiKeXinXiId(paiKeXinXinXiIdList);
        //List<PaiKeJiLu> paiKeJiLuList = paiKeJiLuMapper.getPaiKeJiLuByPaiKeXinXiIdZuAndShangKeXueYuan(paiKeXinXinXiIdList, shangKeXueYuanId);
        return paiKeJiLuList;
    }

    /**
     * 获取老师点名率，根据上课开始日期和上课结束日期
     *
     * @param shangKeLaoShiId
     * @param shangKeRiQiStart
     * @param shangKeRiQiEnd
     * @return
     */
    @Override
    public Double huoQuLaoShiDianMingLv(Long shangKeLaoShiId, Long shangKeRiQiStart, Long shangKeRiQiEnd) {
        if(shangKeLaoShiId == null) {
            throw new BusinessException("请指定要查询点名率的老师");
        }
        Double laoShiDianMingLv = paiKeJiLuMapper.huoQuLaoShiDianMingLv(shangKeLaoShiId, shangKeRiQiStart, shangKeRiQiEnd);
        return laoShiDianMingLv;
    }

    /**
     * 获取老师上课课时，根据上课开始日期和上课结束日期
     *
     * @param shangKeLaoShiId
     * @param shangKeRiQiStart 上课开始日期
     * @param shangKeRiQiEnd   上课结束日期
     * @return
     */
    @Override
    public Double huoQuLaoShiShangKeKeShi(Long shangKeLaoShiId, Long shangKeRiQiStart, Long shangKeRiQiEnd) {
        if(shangKeLaoShiId == null) {
            throw new BusinessException("请指定要查询点名率的老师");
        }
        Double shangKeKeShi = paiKeJiLuMapper.huoQuLaoShiShangKeKeShi(shangKeLaoShiId, shangKeRiQiStart, shangKeRiQiEnd);
        return shangKeKeShi;
    }
}
