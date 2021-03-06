package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoJiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.JiaoFeiJiLuMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.JiaoFeiJiLuRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class JiaoFeiJiLuServiceImp implements JiaoFeiJiLuService {
    @Autowired
    JiaoFeiJiLuRepository jiaoFeiJiLuRepository;

    @Resource
    JiaoFeiJiLuMapper jiaoFeiJiLuMapper;

    /**
     * 创建缴费记录
     * @param cmd
     * @return
     */
    @Transactional
    @Override
    public Long chuangJian(JiaoFeiJiLu.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);
        cmd.setJiaoFeiJiLuZhuangTai(JiaoFeiJiLuZhuangTai.WEI_JIAO_FEI);
        JiaoFeiJiLu jiaoFeiJiLu = JiaoFeiJiLu.chuangJian(cmd);

        jiaoFeiJiLu = jiaoFeiJiLuRepository.save(jiaoFeiJiLu);

        return jiaoFeiJiLu.getId();
    }

    /**
     * 获取缴费记录列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<DtoJiaoFeiJiLu> huoQuJiaoFeiJiLuLieBiao (Integer pageNum, Integer pageSize) {
        IPage<DtoJiaoFeiJiLu> page = new Page<>(pageNum, pageSize);

        return jiaoFeiJiLuMapper.huoQuJiaoFeiJiLu(page);
    }

    /**
     * 根据Id获取缴费记录
     *
     * @param id 缴费记录Id
     * @return
     */
    @Override
    public JiaoFeiJiLu getById(Long id) {
        Optional<JiaoFeiJiLu> jiaoFeiJiLuOptional = jiaoFeiJiLuRepository.findById(id);
        if(!jiaoFeiJiLuOptional.isPresent()) {
           throw new BusinessException("对应缴费记录未找到");
        }
        return jiaoFeiJiLuOptional.get();
    }

    /**
     * 更改缴费记录状态
     *
     * @param id                   缴费记录Id
     * @param jiaoFeiJiLuZhuangTai 缴费记录状态
     */
    @Transactional
    @Override
    public void gengGaiJiaoFenJiLuZhuangTai(Long id, JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai) {
        if(id == null) {
            throw new BusinessException("请指定要查询的缴费记录");
        }
        if(jiaoFeiJiLuZhuangTai == null) {
            throw new BusinessException("请指定缴费记录状态");
        }
        JiaoFeiJiLu jiaoFeiJiLu = getById(id);
        JiaoFeiJiLu.GengGaiZhangTaiCmd cmd = new JiaoFeiJiLu.GengGaiZhangTaiCmd();
        cmd.setJiaoFeiJiLuZhuangTai(jiaoFeiJiLuZhuangTai);
        jiaoFeiJiLu.gengGaiJiaoFeiJiLuZhuangTai(cmd);

        jiaoFeiJiLuRepository.save(jiaoFeiJiLu);
    }
}
