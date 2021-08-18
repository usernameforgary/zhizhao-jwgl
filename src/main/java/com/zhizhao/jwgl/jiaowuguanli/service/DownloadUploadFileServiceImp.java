package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.repository.DownLoadUploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DownloadUploadFileServiceImp implements DownloadUploadFileService {

    @Autowired
    DownLoadUploadFileRepository downLoadUploadFileRepository;
    /**
     * 创建上传下载文件
     *
     * @param chuangJianCmd
     * @return
     */
    @Transactional
    @Override
    public DownloadUploadFile chuangJian(DownloadUploadFile.ChuangJianCmd chuangJianCmd) {
        DownloadUploadFile downloadUploadFile = DownloadUploadFile.chuangJian(chuangJianCmd);
        downLoadUploadFileRepository.save(downloadUploadFile);
        return downloadUploadFile;
    }
}
