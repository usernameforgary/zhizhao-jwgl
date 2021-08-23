package com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjilu;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ChengZhangJiLuLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ShangKeXueYuanLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.XueYuanDaoKeZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChengZhangJiLu extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 学员Id
    @NotNull
    Long xueYuanId;

    // 内容
    @NotNull
    String neiRong;

    // 成长记录文件组
    @ElementCollection
    @Column(name = "chengZhangJiLuWenJianId")
    Set<Long> chengZhangJiLuWenJianZu;

    // 排课记录Id，成长记录类型 = [KE_HOU_DIAN_PING： 课后点评]
    Long paiKeJiLuId;

    // 成长记录类型
    @NotNull
    @Enumerated(EnumType.STRING)
    ChengZhangJiLuLeiXing chengZhangJiLuLeiXing;

    // 家长已读
    @Column(columnDefinition = "boolean default false")
    Boolean jiaZhangYiDu = false;

    /**
     * 成长记录创建
     * @param cmd
     * @return
     */
    public static ChengZhangJiLu chuangJian(ChuangJianCmd cmd) {
        if (cmd.getXueYuanId() == null) {
            throw new BusinessException("请指定成长记录所属学员");
        }
        if(cmd.getChengZhangJiLuLeiXing() == null) {
            throw new BusinessException("请指定成长记录类型");
        } else {
            // 类型为【课后点评】时，排课记录Id不能为空
            if(cmd.getChengZhangJiLuLeiXing().equals(ChengZhangJiLuLeiXing.KE_HOU_DIAN_PING)) {
                if(cmd.getPaiKeJiLuId() == null) {
                    throw new BusinessException("请指定点名所属排课信息");
                }
            }
        }
        ChengZhangJiLu chengZhangJiLu = ChengZhangJiLu.builder()
                .id(cmd.id)
                .xueYuanId(cmd.xueYuanId)
                .neiRong(cmd.neiRong)
                .chengZhangJiLuWenJianZu(cmd.chengZhangJiLuWenJianZu)
                .paiKeJiLuId(cmd.paiKeJiLuId)
                .chengZhangJiLuLeiXing(cmd.chengZhangJiLuLeiXing)
                .jiaZhangYiDu(cmd.jiaZhangYiDu)
                .build();
        return chengZhangJiLu;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        // 学员Id
        @NotNull
        Long xueYuanId;
        // 内容
        String neiRong;
        // 成长记录文件组
        Set<Long> chengZhangJiLuWenJianZu;
        // 排课记录Id，成长记录类型 = [KE_HOU_DIAN_PING： 课后点评]
        Long paiKeJiLuId;
        // 成长记录类型
        @NotNull
        ChengZhangJiLuLeiXing chengZhangJiLuLeiXing;
        // 家长已读
        Boolean jiaZhangYiDu = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChengZhangJiLu that = (ChengZhangJiLu) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1723882039;
    }
}
