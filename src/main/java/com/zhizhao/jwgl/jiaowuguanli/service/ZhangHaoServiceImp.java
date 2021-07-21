package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ZhangHaoMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ZhangHaoServiceImp implements ZhangHaoService {
    @Value("${jiaowuguanli.security.psw.default}")
    private String defaultPsw;

    @Autowired
    ZhangHaoRepository zhangHaoRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    ZhangHaoMapper zhangHaoMapper;

    @Override
    @Transactional
    public Long chuangJianZhangHao(ZhangHao.ChuangJianCmd cmd) {
        if(cmd.getId() == null) {
            cmd.setId(SnowflakeIdUtil.nextId());
        }

        ZhangHao zhangHao = ZhangHao.chuangJian(cmd);
        // 设置默认密码
        if(zhangHao.getMiMa() == null) {
            zhangHao.setMiMa(bCryptPasswordEncoder.encode(defaultPsw));
        }

        zhangHaoRepository.save(zhangHao);
        return zhangHao.getId();
    }

    /**
     * 根据手机和账号类型获取账号信息
     * @param shouJi
     * @param zhangHaoLeiXing
     * @return
     */
    @Override
    public ZhangHao getZhangHaoByShouJiAndLeiXing(String shouJi, ZhangHaoLeiXing zhangHaoLeiXing) {
        return zhangHaoMapper.getZhangHaoByShouJiAndLeiXing(shouJi, zhangHaoLeiXing);
    }

    /**
     * 根据账号类型获取系统账号列表
     * @param zhangHaoLeiXing
     * @return
     */
    @Override
    public List<ZhangHao> getZhangHaoByLeiXing(ZhangHaoLeiXing zhangHaoLeiXing) {
        if(zhangHaoLeiXing == null) {
            throw new BusinessException("请指定要查询的账号类型");
        }
        return zhangHaoMapper.getZhangHaoByLeiXing(zhangHaoLeiXing);
    }
}
