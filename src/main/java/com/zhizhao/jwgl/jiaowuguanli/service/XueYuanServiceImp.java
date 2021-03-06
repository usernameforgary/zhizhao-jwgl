package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.dto.*;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XueYuanMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.XueYuanRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
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
     * 获取学员信息，基本信息
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
     * 分页获取学员列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少条
     * @return
     */
    @Override
    public DtoPageResult<DtoXueYuan> huoQuXueYuanLieBiao(Integer pageNum, Integer pageSize) {
        Page<XueYuan> page = new Page<>(pageNum, pageSize);
        IPage<DtoXueYuan> pageResult = xueYuanMapper.huoQuXueYuanLieBiao(page);

        DtoPageResult<DtoXueYuan> dtoPageResult = Converter.convertPageResultMybatisPlus(pageResult);
        return dtoPageResult;
    }

    /**
     * 分页获取学员列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少条
     * @return
     */
    @Override
    public DtoPageResult<DtoXueYuan> huoQuXueYuanLieBiaoV2(Integer pageNum, Integer pageSize) {
        Page<XueYuan> page = new Page<>(pageNum, pageSize);
        IPage<DtoXueYuan> pageResult = xueYuanMapper.huoQuXueYuanLieBiaoV2(page);

        DtoPageResult<DtoXueYuan> dtoPageResult = Converter.convertPageResultMybatisPlus(pageResult);
        return dtoPageResult;
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

    /**
     * 获取潜在学员列表
     *
     * @param pageNum
     * @param pageSize
     * @param keyword         关键字
     * @param genJinZhuangTai 跟进状态
     * @param genJinRenId     跟进人
     * @return
     */
    @Override
    public DtoPageResult<DtoXueYuanQianZai> huoQuQianZaiXueYuanLieBiao(Integer pageNum, Integer pageSize, String keyword, String genJinZhuangTai, Long genJinRenId) {
        Page<DtoXueYuanQianZai> page = new Page<>(pageNum, pageSize);

        IPage<DtoXueYuanQianZai> dtoXueYuanQianZaiPageResult = xueYuanMapper.huoQuQianZaiXueYuanLieBiao(page, keyword, genJinZhuangTai, genJinRenId);

        DtoPageResult<DtoXueYuanQianZai> dtoPageResult = Converter.convertPageResultMybatisPlus(dtoXueYuanQianZaiPageResult);
        return dtoPageResult;
    }

    /**
     * 获取在读学员列表
     *
     * @param pageNum
     * @param pageSize
     * @param keyword  关键字
     * @param banJiId  班级Id
     * @return
     */
    @Override
    public DtoPageResult<DtoXueYuanZaiDu> huoQuZaiDuXueYuanLieBiao(Integer pageNum, Integer pageSize, String keyword, String banJiId) {
        Page<DtoXueYuanZaiDu> page = new Page<>(pageNum, pageSize);

        IPage<DtoXueYuanZaiDu> dtoXueYuanZaiDuPageResult = xueYuanMapper.huoZaiDuXueYuanLieBiao(page, keyword, banJiId);

        DtoPageResult<DtoXueYuanZaiDu> dtoPageResult = Converter.convertPageResultMybatisPlus(dtoXueYuanZaiDuPageResult);
        return dtoPageResult;
    }

    /**
     * 获取历史学员列表
     *
     * @param pageNum
     * @param pageSize
     * @param keyword     关键字
     * @param keChengId   课程Id
     * @param genJinRenId 跟进人Id
     * @return
     */
    @Override
    public DtoPageResult<DtoXuYuanLiShi> huoQuLiShiXueYuanLieBiao(Integer pageNum, Integer pageSize, String keyword, String keChengId, String genJinRenId) {
        Page<DtoXuYuanLiShi> page = new Page<>(pageNum, pageSize);
        IPage<DtoXuYuanLiShi> dtoXuYuanLiShiPageResult = xueYuanMapper.huoQuLiShiXueYuanLieBiao(page, keyword, keChengId, genJinRenId);

        DtoPageResult<DtoXuYuanLiShi> dtoPageResult = Converter.convertPageResultMybatisPlus(dtoXuYuanLiShiPageResult);
        return dtoPageResult;
    }
}
