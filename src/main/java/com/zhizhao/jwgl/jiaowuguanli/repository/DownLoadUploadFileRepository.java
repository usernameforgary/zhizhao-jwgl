package com.zhizhao.jwgl.jiaowuguanli.repository;

import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDownloadUploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownLoadUploadFileRepository extends JpaRepository<DownloadUploadFile, Long> {
}
