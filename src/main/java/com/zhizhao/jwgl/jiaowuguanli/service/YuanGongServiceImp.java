package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.laoshi.LaoShi;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class YuanGongServiceImp implements YuanGongService {
    @Autowired
    ZhangHaoRepository zhangHaoRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ZhangHaoService zhangHaoService;
    @Autowired
    LaoShiService laoShiService;

    @Override
    @Transactional
    public void tianJianYuanGong(DtoTianJiaYuanGong dto) {
        // 账号 Id
        Long zhangHaoId = SnowflakeIdUtil.nextId();

        // 账号信息
        ZhangHao.ChuangJianCmd zhangHaoChuangJianCmd = new ZhangHao.ChuangJianCmd();
        zhangHaoChuangJianCmd.setId(zhangHaoId);
        zhangHaoChuangJianCmd.setShouJi(dto.getShouJi());
        zhangHaoChuangJianCmd.setXingMing(dto.getXingMing());
        zhangHaoChuangJianCmd.setXingBie(dto.getXingBie());
        zhangHaoChuangJianCmd.setJueSeZu(dto.getJueSeZu());
        zhangHaoChuangJianCmd.setIsLaoShi(dto.getIsLaoShi());
        zhangHaoChuangJianCmd.setBeiZhu(dto.getBeiZhu());
        // 设置账号类型为YUAN_GONG
        zhangHaoChuangJianCmd.setZhangHaoLeiXing(ZhangHaoLeiXing.YUAN_GONG);

        zhangHaoService.chuangJianZhangHao(zhangHaoChuangJianCmd);

        // 如果是老师（页面上选择了授课）
        if(dto.getIsLaoShi()) {
            LaoShi.ChuangJianCmd laoShiChuangJianCmd = new LaoShi.ChuangJianCmd();
            Long laoShiId = SnowflakeIdUtil.nextId();
            laoShiChuangJianCmd.setId(laoShiId);
            laoShiChuangJianCmd.setZhangHaoId(zhangHaoId);
            laoShiChuangJianCmd.setShanChangKeMuZu(dto.getShanChangKeMuZu());

            laoShiService.chuangJianLaoShi(laoShiChuangJianCmd);
        }
    }
}
