package com.zhizhao.jwgl.jiaowuguanli.dto;

import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianFenLei;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianZhuangTai;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDownloadUploadFile {
    @NotNull
    Long id;
    @NotNull
    // 创建时间
    Long createTime;
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
    WenJianFenLei wenJianFenLei;
    // 上传下载文件状态
    @NotNull
    WenJianZhuangTai wenJianZhuangTai;
    // 操作者Id
    @NotNull
    Long zhangHaoId;
    // 账号姓名
    String zhangHaoXingMing;
}
