package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YouHuiLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name="json", typeClass = JsonType.class)
public class XueYuanKeCheng extends AggRoot {
    @Id
    @NotNull
    Long id;

    //学员ID
    @NotNull
    @Column(nullable = false)
    Long xueYuanId;

    //课程ID
    @NotNull
    @Column(nullable = false)
    Long keChengId;

    //定价标准
    @NotNull
    @Type(type="json")
    @Column(columnDefinition = "json")
    DingJiaBiaoZhun dingJiaBiaoZhun;

    //课程状态
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    XueYuanKeChengZhuangTai keChengZhuangTai;

    //课程类型
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    KeChengLeiXing keChengLeiXing;

    //单价
    @NotNull
    @Column(nullable = false)
    Double danJia;

    //课程数量
    @NotNull
    @Column(nullable = false)
    Double keChengShuLiang;

    //赠送课时
    @NotNull
    @Column(nullable = false)
    Double zengSongKeShi;

    //优惠类型
    @NotNull
    @Enumerated(EnumType.STRING)
    YouHuiLeiXing youHuiLeiXing;

    //优惠数量
    @NotNull
    Double youHuiShuLiang;

    // 备注
    String beiZhu;

    // 学员课程有效期限
    Long keChengYouXiaoQi;

    // 剩余课时（课程数量 + 赠送课时 - 消耗课时)
    @NotNull
    Double shengYuKeShi;

    // 消课金额
    @NotNull
    @Column(columnDefinition = "double default 0")
    Double xiaoKeJinE;

    // 创建
    public static XueYuanKeCheng chaungJian(XueYuanKeCheng.ChuangJianCmd cmd) {
        if(cmd.xueYuanId == null) {
            throw new BusinessException("对应学员不能为空");
        }
        if(cmd.keChengId == null) {
            throw new BusinessException("对应课程Id不能为空");
        }
        if(cmd.danJia == null) {
            throw new BusinessException("单价不能为空");
        }
        if(cmd.keChengShuLiang == null || cmd.keChengShuLiang <= 0) {
            throw new BusinessException("请指定课程数量");
        }
        XueYuanKeCheng xueYuanKeCheng = XueYuanKeCheng.builder()
                .id(cmd.id)
                .xueYuanId(cmd.xueYuanId)
                .keChengId(cmd.keChengId)
                .dingJiaBiaoZhun(cmd.dingJiaBiaoZhun)
                .keChengZhuangTai(cmd.keChengZhuangTai)
                .keChengLeiXing(cmd.keChengLeiXing)
                .danJia(cmd.danJia)
                .keChengShuLiang(cmd.keChengShuLiang)
                .zengSongKeShi(cmd.zengSongKeShi)
                .youHuiLeiXing(cmd.youHuiLeiXing)
                .youHuiShuLiang(cmd.youHuiShuLiang)
                .keChengYouXiaoQi(cmd.keChengYouXiaoQi)
                .beiZhu(cmd.beiZhu)
                .shengYuKeShi(cmd.keChengShuLiang + cmd.zengSongKeShi)
                .xiaoKeJinE(new Double(0))
                .build();
        return xueYuanKeCheng;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        @NotNull
        Long xueYuanId;
        @NotNull
        Long keChengId;
        @NotNull
        DingJiaBiaoZhun dingJiaBiaoZhun;
        @NotNull
        XueYuanKeChengZhuangTai keChengZhuangTai;
        @NotNull
        KeChengLeiXing keChengLeiXing;
        @NotNull
        Double danJia;
        @NotNull
        Double keChengShuLiang;
        @NotNull
        Double zengSongKeShi;
        @NotNull
        YouHuiLeiXing youHuiLeiXing;
        @NotNull
        Double youHuiShuLiang;
        Long keChengYouXiaoQi;
        String beiZhu;
        Double shengYuKeShi;
        @NotNull
        Double xiaoKeJinE;
    }

    /**
     * （学员选班）更改学员课程状态
     */
    public void xueYuanXuanBanGengGaiKeChengZhuangTai() {
        if(keChengZhuangTai.equals(XueYuanKeChengZhuangTai.DAI_QUE_REN)) {
            throw new BusinessException("学员课程【待确认】，不能选班");
        }
        if(keChengZhuangTai.equals(XueYuanKeChengZhuangTai.DAI_JIE_KE)) {
            throw new BusinessException("学员课程【待结课】，不能选班");
        }
        if(keChengZhuangTai.equals(XueYuanKeChengZhuangTai.YI_JIE_KE)) {
            throw new BusinessException("学员课程【已结课】，不能选班");
        }
//        if(!(keChengZhuangTai.equals(XueYuanKeChengZhuangTai.DAI_BU_JIAO) || keChengZhuangTai.equals(XueYuanKeChengZhuangTai.DAI_PAI_KE))) {
//            throw new BusinessException("学员课程【待补缴】或【待排课】时，才能选班");
//        }
        setKeChengZhuangTai(XueYuanKeChengZhuangTai.DAI_SHANG_KE);
    }

    // 更改学员课程状态
    public void gengGaiXueYuanKeChengZhangTai(XueYuanKeChengZhuangTai xueYuanKeChengZhuangTai) {
        setKeChengZhuangTai(xueYuanKeChengZhuangTai);
    }

    /**
     * 缴费确认更改学员课程状态
     * @param cmd
     */
    public void jiaoFeiQueRenGengGaiZhuangTai(GengGaiKeChengZhuangTaiCmd cmd) {
        setKeChengZhuangTai(cmd.keChengZhuangTai);
    }

    /**
     * 点名扣课时，更新剩余课时、消课金额、课程状态
     * @param kouChuKeShi 扣除课时数量
     * @return 本次课消金额
     */
    public Double dianMingGengXinShengYuKeShiXiaoKeJinE(Double kouChuKeShi) {
        // 学员课程状态为【待上课】或【待补缴】，可以排课上课
        if(!(XueYuanKeChengZhuangTai.DAI_SHANG_KE.equals(getKeChengZhuangTai()) || XueYuanKeChengZhuangTai.DAI_BU_JIAO.equals(getKeChengZhuangTai()))) {
            throw new BusinessException("不能点名, 存在课程状态为【" + Converter.convertXueYuanKeChengZhuangTai2String(getKeChengZhuangTai()) + "】的记录");
        }
        Double keXiaoJinE = 0.0;
        // 赠送课时
        Double zengSongKeShi = getZengSongKeShi();
        // 剩余课时
        Double shengYuKeShi = getShengYuKeShi();
        // 剩余课时大于等于赠送课时
        if(shengYuKeShi >= zengSongKeShi) {
            // 实际课程单价
            Double shiJiKeChengDanJia = this.jiSuanShiJiDanJia();
            keXiaoJinE = shiJiKeChengDanJia * kouChuKeShi;
        }
        setShengYuKeShi(getShengYuKeShi() - kouChuKeShi);
        setXiaoKeJinE(getXiaoKeJinE() + keXiaoJinE);
        // 更改课程状态
//        if(getShengYuKeShi() <= 0 ) {
//            setKeChengZhuangTai(XueYuanKeChengZhuangTai.DAI_JIE_KE);
//        }
        return keXiaoJinE;
    }

    /**
     * 计算课程实际单价
     * @return
     */
    public Double jiSuanShiJiDanJia() {
        // 单价
        Double danJia = getDanJia();
        // 课程数量
        Double keChengShuLiang = getKeChengShuLiang();
        // 赠送课时
        Double zengSongKeShi = getZengSongKeShi();
        // 优惠类型
        YouHuiLeiXing youHuiLeiXing = getYouHuiLeiXing();
        // 优惠数量
        Double youHuiShuLiang = getYouHuiShuLiang();
        // 实际单价
        Double shiJiDanJia = 0.0;
        if(YouHuiLeiXing.ZHI_JIAN.equals(youHuiLeiXing)) {
            shiJiDanJia = (danJia * keChengShuLiang - youHuiShuLiang) / keChengShuLiang;
        } else if (YouHuiLeiXing.ZHE_KOU.equals(youHuiLeiXing)){
            shiJiDanJia = ((danJia * keChengShuLiang) * (1 - youHuiShuLiang/100)) / keChengShuLiang;
        } else {
            throw new BusinessException("未知的优惠类型");
        }
        return shiJiDanJia;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GengGaiKeChengZhuangTaiCmd {
        XueYuanKeChengZhuangTai keChengZhuangTai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        XueYuanKeCheng that = (XueYuanKeCheng) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 847751178;
    }
}
