package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XiTongCaiDanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XiTongCaiDanServiceImp implements XiTongCaiDanService{
    @Resource
    XiTongCaiDanMapper xiTongCaiDanMapper;

    /**
     * 根据ZhangHaoId查询账号下的系统菜单
     * @param zhangHaoId
     * @return
     */
    @Override
    public List<XiTongCaiDan> huoQuXiTongCanDanByZhangHaoId(Long zhangHaoId) {
        if(zhangHaoId == null) {
            throw new BusinessException("请提供要查询的账号");
        }
        return xiTongCaiDanMapper.huoQuXiTongCaiDanByZhangHaoId(zhangHaoId);
    }
}
