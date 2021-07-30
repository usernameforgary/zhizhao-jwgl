package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BanJiMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.BanJiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BanJIServiceImp implements BanJiService{
    @Resource
    BanJiMapper banJiMapper;

    @Autowired
    BanJiRepository banJiRepository;

    @Override
    public IPage<DtoBanJi> huoQuBanJiLieBiao(Integer pageNum, Integer pageSize) {
        Page<BanJi> page = new Page<>(pageNum, pageSize);

        return  banJiMapper.huoQuBanJiLeiBiao(page);
    }

    /**
     * 获取班级详情
     * @param id
     * @return
     */
    @Override
    public DtoBanJi huoQuBanJiXiangQing(Long id) {
        if(id == null) {
            throw new BusinessException("请提供需要查询的班级");
        }
        return banJiMapper.huoQuBanJiXiangQing(id);
    }

    /**
     * 根据课程Id，获取选择了该课程的班级列表
     * @param keChengId 课程Id
     * @return
     */
    @Override
    public List<DtoBanJi> huoQuBanJiByKeChengId(Long keChengId) {
        if(keChengId == null) {
            throw new BusinessException("请指定要查询的班级所属课程");
        }
        return banJiMapper.huoQuBanJiByKeChengId(keChengId);
    }

    /**
     * 添加班级学员
     *
     * @param banJiId 班级Id
     * @param xueYuanId 学员Id
     */
    @Transactional
    @Override
    public void tianJiaBanJiXueYuan(Long banJiId, Long xueYuanId) {
        Optional<BanJi> banJiOptional = banJiRepository.findById(banJiId);
        if(banJiOptional.isPresent()) {
            BanJi banJi = banJiOptional.get();
            BanJi.TianJiaXueYuanCmd tianJiaXueYuanCmd = new BanJi.TianJiaXueYuanCmd();
            tianJiaXueYuanCmd.setXueYuanId(xueYuanId);
            banJi.tianJiaXueYuan(tianJiaXueYuanCmd);
            banJiRepository.save(banJi);
        } else {
            throw new BusinessException("未找到指定班级");
        }
    }

    /**
     * 删除班级学员
     *
     * @param banJiId   班级Id
     * @param xueYuanId 学员Id
     */
    @Transactional
    @Override
    public void shanChuBanJiXueYuan(Long banJiId, Long xueYuanId) {
        Optional<BanJi> banJiOptional = banJiRepository.findById(banJiId);
        if(banJiOptional.isPresent()) {
            BanJi banJi = banJiOptional.get();

            BanJi.ShanChuXueYuanCmd shanChuXueYuanCmd = new BanJi.ShanChuXueYuanCmd();
            shanChuXueYuanCmd.setXueYuanId(xueYuanId);
            banJi.shanChuXueYuan(shanChuXueYuanCmd);
            banJiRepository.save(banJi);
        } else {
            throw new BusinessException("未找到指定班级");
        }
    }

    /**
     * 根据班级id，获取班级学员
     *
     * @param banJiId 班级Id
     * @return
     */
    @Override
    public List<DtoBanJiXueYuan> huoQuBanJiXueYuanByBanJiId(Long banJiId) {
        if(banJiId == null) {
            throw new BusinessException("请指定要查找的班级");
        }
        List<DtoBanJiXueYuan> result = banJiMapper.huoQuBanJiXueYuanByBanJiId(banJiId);
        return result;
    }
}
