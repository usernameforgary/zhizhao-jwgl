package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiJiLu;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.repository.JiaoFeiJiLuRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JiaoFeiJiLuServiceImp implements JiaoFeiJiLuService {
    @Autowired
    JiaoFeiJiLuRepository jiaoFeiJiLuRepository;
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
}
