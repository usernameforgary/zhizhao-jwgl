package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanKeCheng;

import java.util.List;

public interface XueYuanKeChengService {
    /**
     * 根据学员Id，获取学员已选课程，信息（包含所属课程信息，班级信息，班级老师信息)
     * @param xueYuanId 学员Id
     * @param isLiShi 是否是历史课程（已结课的课程，XueYuanKeChengZhuangTai.YI_JIE_KE）
     * @return
     */
    List<DtoXueYuanKeCheng> getXueYuanKeChengByXueYuanId(Long xueYuanId, boolean isLiShi);

    /**
     * 根据Id，获取学员课程信息（包含所属课程信息，班级信息，班级老师信息)
     * @param id 学员课程id
     * @return
     */
    DtoXueYuanKeCheng getXueYuanKeChengById(Long id);

    /**
     * 保存所有学员课程
     * @param xueYuanKeChengList
     * @return
     */
    List<XueYuanKeCheng> saveAllXueYuanKeCheng(List<XueYuanKeCheng> xueYuanKeChengList);

    /**
     * 学员选班，更改学员课程状态
     * @param xueYuanKeChengId
     */
    void xuanBanGengGaiKeChengZhuangTai(Long xueYuanKeChengId);

    /**
     * 根据Id，获取（未结课，未删除）的学员课程
     * @param id 学员课程Id
     * @return
     */
    XueYuanKeCheng getById(Long id);

    /**
     * 根据id组获取学员课程
     * @param ids 学员课程id列表
     * @return
     */
    List<XueYuanKeCheng> getByIds(List<Long> ids);

    /**
     * 根据学员Id和课程Id获取学员课程
     * @param xueYuanId
     * @param keChengId
     * @return
     */
    XueYuanKeCheng getXueYuanKeChengByXueYuanIdAndKeChengId(Long xueYuanId, Long keChengId);

    /**
     * 根据学员Id, 获取不包含当前学员课程Id的，状态!=【已结课】的学员课程
     * @param xueYuanId
     * @param excludedXueYuanKeChengId
     * @return
     */
    List<XueYuanKeCheng> getQiTaWeiJieKeCheng(Long xueYuanId, Long excludedXueYuanKeChengId);
}
