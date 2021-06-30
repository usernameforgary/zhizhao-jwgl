package com.zhizhao.jwgl.jiaowuguanli.vo;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XingBie;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ZhangHaoVo {
        Long id;
        Long createTime;
        Long updateTime;
        Integer version;
        Boolean isDeleted;
        String xingMing;
        String shouJi;
        XingBie xingBie;
        String miMa;
        Set<String> jueSeZu;
        ZhangHaoLeiXing zhangHaoLeiXing;
        Boolean isLaoShi = false;
        String beiZhu;
        Boolean zaiZhiZhuangTai = true;
        List<XiTongCaiDan> xitongCaiDanZu;
}
