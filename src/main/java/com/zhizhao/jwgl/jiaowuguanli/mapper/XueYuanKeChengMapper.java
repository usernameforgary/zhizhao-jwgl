package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.XueYuanKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoXueYuanKeCheng;

import java.util.List;

public interface XueYuanKeChengMapper extends MyBaseMapper<XueYuanKeCheng> {
    /**
     * 根据学员Id，获取学员已选课程，信息（包含所属课程信息，班级信息，班级老师信息)
     * @param xueYuanId 学员Id
     * @param isLiShi 是否是历史课程（已结课的课程，XueYuanKeChengZhuangTai.YI_JIE_KE）
     * @return
     */
    List<DtoXueYuanKeCheng> getXueYuanKeChengByXueYuanId(Long xueYuanId, boolean isLiShi);

    /**
     * 根据Id，获取学员课程信息（包含所属课程信息，班级信息，班级老师信息)
     *
     * @param id 学员课程id
     * @return
     */
    DtoXueYuanKeCheng getXueYuanKeChengById(Long id);
}
