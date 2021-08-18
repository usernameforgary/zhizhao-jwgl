package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;

public interface DownloadUploadFileService {
     /**
      * 创建上传下载文件
      * @param chuangJianCmd
      * @return
      */
    DownloadUploadFile chuangJian(DownloadUploadFile.ChuangJianCmd chuangJianCmd);
}
