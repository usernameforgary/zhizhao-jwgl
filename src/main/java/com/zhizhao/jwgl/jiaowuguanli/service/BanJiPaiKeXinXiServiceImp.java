package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.paike.BanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiPaiKeXinXi;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BanJiPaiKeXinXiMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.BanJiPaiKeXinXiRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BanJiPaiKeXinXiServiceImp implements BanJiPaiKeXinXiService {
    @Autowired
    BanJiPaiKeXinXiRepository banJiPaiKeXinXiRepository;

    @Resource
    BanJiPaiKeXinXiMapper banJiPaiKeXinXiMapper;

    /**
     * 创建班级排课信息
     * @param cmd
     * @return
     */
    @Transactional
    @Override
    public Long chuangJianBanJiPaiKeXinXi(BanJiPaiKeXinXi.ChuangJianCommand cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);
        BanJiPaiKeXinXi banJiPaiKeXinXi = BanJiPaiKeXinXi.chuangJian(cmd);
        banJiPaiKeXinXiRepository.save(banJiPaiKeXinXi);
        return id;
    }

    /**
     * 获取班级排课信息
     * @param banJiId
     * @return
     */
    @Override
    public List<DtoBanJiPaiKeXinXi> huoQuBanJiPaiKeXinXi(Long banJiId) {
        return banJiPaiKeXinXiMapper.huoQuBanJiPaiKeXinXi(banJiId);
    }
}
