package com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile;

import com.zhizhao.jwgl.jiaowuguanli.domain.AggRoot;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.BuKeZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ChengZhangJiLuLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianFenLei;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 上传下载文件
 */
@Entity
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DownloadUploadFile extends AggRoot {
    @Id
    @NotNull
    Long id;

    // 文件名称
    @NotNull
    String mingCheng;

    // 后缀
    @NotNull
    String houZhui;

    // oss上key(文件名)
    @NotNull
    String ossKey;

    // 大小
    @NotNull
    Long daXiao;

    // 上传下载文件分类
    @NotNull
    @Enumerated(EnumType.STRING)
    WenJianFenLei wenJianFenLei;

    // 上传下载文件状态
    @NotNull
    @Enumerated(EnumType.STRING)
    WenJianZhuangTai wenJianZhuangTai;

    // 创建
    public static DownloadUploadFile chuangJian(ChuangJianCmd cmd) {
        if(cmd.id == null) {
            throw new BusinessException("请提供文件Id");
        }
        if(cmd.mingCheng == null) {
            throw new BusinessException("文件名称不能为空");
        }
        if(cmd.ossKey == null) {
            throw new BusinessException("请提供oss key");
        }
        if(cmd.houZhui == null) {
            throw new BusinessException("文件格式不能为空");
        }
        if(cmd.daXiao == null) {
            throw new BusinessException("文件大小不能为空");
        }
        if(cmd.wenJianFenLei == null) {
            throw new BusinessException("文件类型不能为空");
        }
        if(cmd.wenJianZhuangTai == null) {
            throw new BusinessException("文件状态不能为空");
        }
        DownloadUploadFile downloadUploadFile = DownloadUploadFile.builder()
                .id(cmd.id)
                .mingCheng(cmd.mingCheng)
                .ossKey(cmd.ossKey)
                .houZhui(cmd.houZhui)
                .daXiao(cmd.daXiao)
                .wenJianFenLei(cmd.wenJianFenLei)
                .wenJianZhuangTai(cmd.wenJianZhuangTai)
                .build();
        return downloadUploadFile;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChuangJianCmd {
        @NotNull
        Long id;
        // 文件名称
        @NotNull
        String mingCheng;
        // 后缀
        @NotNull
        String houZhui;
        // oss上key(文件名)
        @NotNull
        String ossKey;
        // 大小
        @NotNull
        Long daXiao;
        // 上传下载文件分类
        @NotNull
        @Enumerated(EnumType.STRING)
        WenJianFenLei wenJianFenLei;
        // 上传下载文件状态
        @NotNull
        @Enumerated(EnumType.STRING)
        WenJianZhuangTai wenJianZhuangTai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DownloadUploadFile that = (DownloadUploadFile) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 549056902;
    }
}
