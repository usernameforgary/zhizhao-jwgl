package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.jiaofeijilu.JiaoFeiLiShi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 学员报名DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoXueYuanBaoMing {
    // 学员信息
    @NotNull
    DtoXueYuan xueYuanXinXi;

    // 学员课程列表
    @NotNull
    List<DtoXueYuanKeCheng> xueYuanKeChengList;

    // 缴费记录 - 缴费历史（一个缴费记录可能多次缴费，报名时缴费为第一次缴费）
    @NotNull
    JiaoFeiLiShi jiaoFeiLiShi;

    // 报名课程的有效期，多个课程时，共用一个有效期
    Long keChengYouXiaoQi;

    // 跟进人Id，这里是缴费记录里的跟进人，跟学员里的跟进人不是同一个
    Long genJinRenId;
}


