package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import org.springframework.core.io.Resource;

public interface DownloadUploadFileService {
     /**
      * 创建上传下载文件
      * @param chuangJianCmd
      * @return
      */
    DownloadUploadFile chuangJian(DownloadUploadFile.ChuangJianCmd chuangJianCmd);

    /**
     * 根据账号Id获取待下载的文件数量
     * @param zhangHaoId 账号Id
     * @return
     */
    Integer huoQuDaiXiaZaiWenJianShuLiangById(Long zhangHaoId);

    /**
     * 根据账号Id，分页获取类型为【下载】的文件列表
     * @param pageNum
     * @param pageSize
     * @param zhangHaoId
     * @return
     */
    DtoPageResult<DtoDownloadUploadFile> huoQuDaiXiaZaiWenJianShuByZhangHaoId(Integer pageNum, Integer pageSize, Long zhangHaoId);

    /**
     * 根据Id下载文件
     * @param id
     * @return
     */
    String xiaZaiWenJianById(Long id);
}
