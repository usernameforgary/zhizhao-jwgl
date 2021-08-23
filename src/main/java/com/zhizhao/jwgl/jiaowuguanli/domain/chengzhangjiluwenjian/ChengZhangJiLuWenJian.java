package com.zhizhao.jwgl.jiaowuguanli.domain.chengzhangjiluwenjian;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChengZhangJiLuWenJian extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 名称
    String mingCheng;

    // 后缀
    @NotNull
    String houZhui;

    // oss上key
    @NotNull
    String ossKey;

    // oss的bucket名称
    String ossBucketName;

    // 大小
    @NotNull
    Integer daXiao;

    /**
     * 创建成长记录文件
     * @param cmd
     * @return
     */
    public static ChengZhangJiLuWenJian chuangJian(ChuangJianCmd cmd) {
        if(cmd.id == null) {
            cmd.setId(SnowflakeIdUtil.nextId());
        }
        if(cmd.mingCheng == null)  {
            throw new BusinessException("成长记录文件名称不能为空");
        }
        if(cmd.ossKey == null) {
            throw new BusinessException("成长记录文件oss地址不能为空");
        }
        if(cmd.getHouZhui() == null) {
            throw new BusinessException("成长记录文件后缀不能为空");
        }
        ChengZhangJiLuWenJian chengZhangJiLuWenJian = ChengZhangJiLuWenJian.builder()
                .build();
        chengZhangJiLuWenJian.setId(cmd.id);
        chengZhangJiLuWenJian.setMingCheng(cmd.mingCheng);
        chengZhangJiLuWenJian.setHouZhui(cmd.houZhui);
        chengZhangJiLuWenJian.setOssKey(cmd.ossKey);
        chengZhangJiLuWenJian.setOssBucketName(cmd.ossBucketName);
        chengZhangJiLuWenJian.setDaXiao(cmd.daXiao);

        return chengZhangJiLuWenJian;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        // 名称
        String mingCheng;
        // 后缀
        @NotNull
        String houZhui;
        // oss上key
        @NotNull
        String ossKey;
        // oss的bucket名称
        String ossBucketName;
        // 大小
        @NotNull
        Integer daXiao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChengZhangJiLuWenJian that = (ChengZhangJiLuWenJian) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 28131706;
    }
}
