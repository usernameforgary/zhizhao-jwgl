package com.zhizhao.jwgl.jiaowuguanli.domain.kecheng;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.KeChengLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanKeChengZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.YouHuiLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xueyuan.XueYuan;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
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

    /**
     * 缴费确认更改学员课程状态
     * @param cmd
     */
    public void jiaoFeiQueRenGengGaiZhuangTai(GengGaiKeChengZhuangTaiCmd cmd) {
        setKeChengZhuangTai(cmd.keChengZhuangTai);
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
