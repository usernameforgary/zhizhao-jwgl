package com.zhizhao.jwgl.jiaowuguanli.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanZaiDu;
import com.zhizhao.jwgl.jiaowuguanli.mapper.LaoShiMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.LaoShiRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class LaoShiServiceImp implements LaoShiService {
    @Autowired
    LaoShiRepository laoShiRepository;

    @Resource
    LaoShiMapper laoShiMapper;

    @Autowired
    PaiKeJiLuService paiKeJiLuService;

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

    /**
     * 获取老师列表
     *
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字【老师姓名 | 手机号】
     * @param zaiZhiZhuangTai 在职状态
     * @return
     */
    @Override
    public DtoPageResult<DtoLaoShi> huoQuLaoShiLieBiao(Integer pageNum, Integer pageSize, String keyword, Boolean zaiZhiZhuangTai) {
        Page<DtoLaoShi> page = new Page<>(pageNum, pageSize);
        IPage<DtoLaoShi> dtoLaoShiIPageResult = laoShiMapper.huoQuLaoShiLieBiao(page, keyword, zaiZhiZhuangTai);
        // 获取老师上月点名率

        // 当前日期
        Date now = new Date();
        Long beginOfThisMonth = DateUtil.beginOfMonth(now).getTime();
        Long endOfThisMonth = DateUtil.endOfMonth(now).getTime();
        // 上个月今天
        Date prevMonthNow = DateUtil.lastMonth();
        Long beginOfPrevMonth = DateUtil.beginOfMonth(prevMonthNow).getTime();
        Long endOfPrevMonth = DateUtil.endOfMonth(prevMonthNow).getTime();

        // 获取老师上月点名率、上月课时、本月课时、已上课时
        List<DtoLaoShi> dtoLaoShiList = dtoLaoShiIPageResult.getRecords();
        for(DtoLaoShi dtoLaoShi : dtoLaoShiList) {
            // 上月点名率
            Double dianMingLv = paiKeJiLuService.huoQuLaoShiDianMingLv(dtoLaoShi.getId(), beginOfPrevMonth, endOfPrevMonth);
            dtoLaoShi.setShangYueDianMingLv(dianMingLv);
            // 上月课时
            Double shangYueKeShi = paiKeJiLuService.huoQuLaoShiShangKeKeShi(dtoLaoShi.getId(), beginOfPrevMonth, endOfPrevMonth);
            dtoLaoShi.setShangYueKeShi(shangYueKeShi);
            // 本月课时
            Double benYueKeShi = paiKeJiLuService.huoQuLaoShiShangKeKeShi(dtoLaoShi.getId(), beginOfThisMonth, endOfThisMonth);
            dtoLaoShi.setBenYueKeShi(benYueKeShi);
            // 已上课时
            Double yiShangKeShi = paiKeJiLuService.huoQuLaoShiShangKeKeShi(dtoLaoShi.getId(), null, null);
            dtoLaoShi.setYiShangKeShi(yiShangKeShi);
        }

        DtoPageResult<DtoLaoShi> dtoLaoShiPageResult = Converter.convertPageResultMybatisPlus(dtoLaoShiIPageResult);
        return dtoLaoShiPageResult;
    }
}
