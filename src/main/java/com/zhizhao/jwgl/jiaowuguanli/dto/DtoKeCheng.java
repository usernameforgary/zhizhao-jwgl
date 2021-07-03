package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.DingJiaBiaoZhun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoKeCheng {
    Long id;
    //名称
    String mingCheng;
    //单价
    Double danJia;
    //启用状态
    Boolean qiYongZhuangTai;
    //定价标准
    List<DingJiaBiaoZhun> dingJiaBiaoZhunZu;
    //请假扣课时
    Boolean qingJiaKouKeShi = false;
    //未到扣课时
    Boolean weiDaoKouKeShi = false;
    //备注
    String beiZhu;
    // 在读学员数
    Integer zaiDuXueYuanShu;
}
