package com.zhizhao.jwgl.jiaowuguanli.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianFenLei;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDownloadUploadFile;

import java.util.List;

public interface DownloadUploadFileMapper extends MyBaseMapper<DownloadUploadFile>{
    /**
     * 根据账号Id，文件分类，文件状态获取文件信息,
     * @param page  分页信息。【需要获取全部时，page.pageSize=-1】
     * @param zhangHaoId 账号Id
     * @param wenJianFenLei 文件分类
     * @param wenJianZhuangTai 文件状态
     * @return
     */
    IPage<DtoDownloadUploadFile> getByZhangHaoIdAndFenLeiAndZhuangTai(Page page, Long zhangHaoId, WenJianFenLei wenJianFenLei, WenJianZhuangTai wenJianZhuangTai);
}
