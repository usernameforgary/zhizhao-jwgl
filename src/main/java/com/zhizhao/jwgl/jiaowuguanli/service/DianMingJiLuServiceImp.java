package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.dianmingjilu.DianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDianMingJiLuQuery;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPaiKeJiLu;
import com.zhizhao.jwgl.jiaowuguanli.mapper.DianMingJiLuMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.DianMingJiLuRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DianMingJiLuServiceImp implements DianMingJiLuService {
    @Autowired
    DianMingJiLuRepository dianMingJiLuRepository;

    @Resource
    DianMingJiLuMapper dianMingJiLuMapper;

    /**
     * 创建点名记录
     *
     * @param chuangJianCmd
     * @return
     */
    @Transactional
    @Override
    public DianMingJiLu chuangJian(DianMingJiLu.ChuangJianCmd chuangJianCmd) {
        DianMingJiLu dianMingJiLu = DianMingJiLu.chuangJian(chuangJianCmd);
        dianMingJiLu =  dianMingJiLuRepository.save(dianMingJiLu);
        return dianMingJiLu;
    }

    /**
     * 分页获取点名记录
     *
     * @param dtoDianMingJiLuQuery
     * @return
     */
    @Override
    public DtoPageResult<DtoDianMingJiLu> huoQuDianMingJiLu(DtoDianMingJiLuQuery dtoDianMingJiLuQuery) {
        Page<DtoDianMingJiLu> page = new Page<>(dtoDianMingJiLuQuery.getPageNum(), dtoDianMingJiLuQuery.getPageSize());

        IPage<DtoDianMingJiLu> dianMingJiLuPageResult = dianMingJiLuMapper.getDianMingJiLuList(
                page,
                dtoDianMingJiLuQuery.getXueYuanId(),
                dtoDianMingJiLuQuery.getShangKeRiQiBegin(),
                dtoDianMingJiLuQuery.getShangKeRiQiEnd(),
                dtoDianMingJiLuQuery.getBanJiId(),
                dtoDianMingJiLuQuery.getShangKeLaoShiId());

        DtoPageResult<DtoDianMingJiLu> dtoPageResult = Converter.convertPageResultMybatisPlus(dianMingJiLuPageResult);

        return dtoPageResult;
    }
}
