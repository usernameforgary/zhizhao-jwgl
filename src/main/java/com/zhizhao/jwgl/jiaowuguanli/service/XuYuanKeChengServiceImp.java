package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.repository.XueYuanKeChengRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XuYuanKeChengServiceImp implements XueYuanKeChengService {

    @Autowired
    XueYuanKeChengRepository xueYuanKeChengRepository;

    @Override
    public List<XueYuanKeCheng> getXueYuanKeChengByXueYuanId(Long xueYuanId) {
        return null;
    }

    /**
     * 保存所有学员课程
     * @param xueYuanKeChengList
     * @return
     */
    @Override
    public List<XueYuanKeCheng> saveAllXueYuanKeCheng(List<XueYuanKeCheng> xueYuanKeChengList) {
        return xueYuanKeChengRepository.saveAll(xueYuanKeChengList);
    }
}
