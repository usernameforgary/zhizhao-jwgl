package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.XueYuanKeChengMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.XueYuanKeChengRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class XuYuanKeChengServiceImp implements XueYuanKeChengService {

    @Autowired
    XueYuanKeChengRepository xueYuanKeChengRepository;

    @Resource
    XueYuanKeChengMapper xueYuanKeChengMapper;

    /**
     * 根据Id，获取（未结课，未删除）的学员课程
     * @param id 学员课程Id
     * @return
     */
    @Override
    public XueYuanKeCheng getById(Long id) {
        return xueYuanKeChengRepository.findByIdAndKeChengZhuangTaiIsNotAndIsDeletedIsFalse(id, XueYuanKeChengZhuangTai.YI_JIE_KE);
    }

    /**
     * 根据学员Id，获取学员已选课程，信息（包含所属课程信息，班级信息，班级老师信息)
     * @param xueYuanId 学员Id
     * @param isLiShi 是否是历史课程（已结课的课程，XueYuanKeChengZhuangTai.YI_JIE_KE）
     * @return
     */
    @Override
    public List<DtoXueYuanKeCheng> getXueYuanKeChengByXueYuanId(Long xueYuanId, boolean isLiShi) {
        return xueYuanKeChengMapper.getXueYuanKeChengByXueYuanId(xueYuanId, isLiShi);
    }

    /**
     * 根据Id，获取学员课程信息（包含所属课程信息，班级信息，班级老师信息)
     *
     * @param id 学员课程id
     * @return
     */
    @Override
    public DtoXueYuanKeCheng getXueYuanKeChengById(Long id) {
        return xueYuanKeChengMapper.getXueYuanKeChengById(id);
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

    /**
     * 学员选班，更改学员课程状态
     *
     * @param xueYuanKeChengId
     */
    @Transactional
    @Override
    public void xuanBanGengGaiKeChengZhuangTai(Long xueYuanKeChengId) {
        if(xueYuanKeChengId == null) {
            throw new BusinessException("请指定要查找的学员课程");
        }
        Optional<XueYuanKeCheng> xueYuanKeChengOptional = xueYuanKeChengRepository.findById(xueYuanKeChengId);
        if(xueYuanKeChengOptional.isPresent()) {
            XueYuanKeCheng xueYuanKeCheng = xueYuanKeChengOptional.get();
            xueYuanKeCheng.xueYuanXuanBanGengGaiKeChengZhuangTai();
            xueYuanKeChengRepository.save(xueYuanKeCheng);
        } else {
            throw new BusinessException("未找到指定的学员课程");
        }
    }

    /**
     * 根据id组获取学员课程
     *
     * @param ids 学员课程id列表
     * @return
     */
    @Override
    public List<XueYuanKeCheng> getByIds(List<Long> ids) {
        return xueYuanKeChengRepository.findByIdIn(ids);
    }
}
