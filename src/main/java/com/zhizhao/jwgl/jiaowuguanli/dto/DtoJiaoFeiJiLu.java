package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.JiaoFeiJiLuZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiLiShi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 缴费记录Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoJiaoFeiJiLu {
    Long id;
    // 学员Id
    Long xueYuanId;
    // 学员姓名
    String xueYuanXingMing;
    // 学员课程组
    List<DtoXueYuanKeCheng> xueYuanKeChengZu;
    // 缴费记录状态
    JiaoFeiJiLuZhuangTai jiaoFeiJiLuZhuangTai;
    // 跟进人Id
    Long genJinRenId;
    // 跟进人姓名
    String genJinRenXingMing;
    // 缴费历史组
    List<JiaoFeiLiShi> jiaoFeiLiShiZu;
}
