package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XueYuanMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.XueYuanRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class XueYuanServiceImp implements XueYuanService {
    @Autowired
    XueYuanRepository xueYuanRepository;

    @Resource
    XueYuanMapper xueYuanMapper;

    /**
     * 创建学员
     * @param cmd
     * @return
     */
    @Transactional
    @Override
    public Long chuangJianXueYuan(XueYuan.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);
        XueYuan xueYuan = XueYuan.chuangJian(cmd);
        xueYuanRepository.save(xueYuan);
        return id;
    }

    /**
     * 获取学员信息
     * @param xueYuanId 学员Id
     * @return
     */
    @Override
    public DtoXueYuan huoQuXueYuanXinXi(Long xueYuanId) {
        if(xueYuanId == null) {
            throw new BusinessException("请指定要查找的学员");
        }
        DtoXueYuan dtoXueYuan = xueYuanMapper.huoQuXueYuanXinXi(xueYuanId);
        if(dtoXueYuan == null) {
            throw new BusinessException("未找到指定的学员");
        }
        return dtoXueYuan;
    }

    /**
     * 获取所有学员
     * @return
     */
    @Override
    public List<DtoXueYuan> huoQuXueYuanAll() {
        List<DtoXueYuan> dtoXueYuanList = xueYuanMapper.huoQuXueYuanAll();
        return dtoXueYuanList;
    }

    /**
     * 根据姓名和所属账号手机号获取学员
     * @param xingMing 学员
     * @param shouJi 账号手机号
     * @return
     */
    @Override
    public DtoXueYuan huoQuXueYuanByXinMingAndZhangHaoShouJi(String xingMing, String shouJi) {
        if(xingMing == null || shouJi == null) {
            throw new BusinessException("请指定姓名和手机号");
        }
        return xueYuanMapper.huoQuXueYuanByXinMingAndZhangHaoShouJi(xingMing, shouJi);
    }

    /**
     * 更新学员保存
     * @param xueYuan
     */
    @Transactional
    @Override
    public void gengXinXueYuan(XueYuan xueYuan) {
        xueYuanRepository.save(xueYuan);
    }

    /**
     * 根据Id获取学员
     * @param id 学员id
     * @return
     */
    @Override
    public Optional<XueYuan> huoQuXueYuanById(Long id) {
        return xueYuanRepository.findById(id);
    }
}
