package com.zhizhao.jwgl.jiaowuguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianFenLei;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.WenJianZhuangTai;
import com.zhizhao.jwgl.jiaowuguanli.domain.downloaduploadfile.DownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.mapper.DownloadUploadFileMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.DownLoadUploadFileRepository;
import com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun.OSSHelper;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class DownloadUploadFileServiceImp implements DownloadUploadFileService {

    @Autowired
    DownLoadUploadFileRepository downLoadUploadFileRepository;

    @Resource
    DownloadUploadFileMapper downloadUploadFileMapper;

    @Autowired
    OSSHelper ossHelper;
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

    /**
     * 根据账号Id获取未下载的文件数量
     *
     * @param zhangHaoId
     * @return
     */
    @Override
    public Integer huoQuDaiXiaZaiWenJianShuLiangById(Long zhangHaoId) {
        if(zhangHaoId == null) {
            throw new BusinessException("请提供要查询的账号");
        }
        // 获取全部时，pageSize设置为-1
        Page<DtoDownloadUploadFile> page = new Page<>(1, -1);
        IPage<DtoDownloadUploadFile> dtoDownloadUploadFileIPage  = downloadUploadFileMapper.getByZhangHaoIdAndFenLeiAndZhuangTai(page, zhangHaoId, WenJianFenLei.DOWNLOAD, WenJianZhuangTai.WEI_XIA_ZAI);
        return dtoDownloadUploadFileIPage.getRecords().size();
    }

    /**
     * 根据账号Id，分页获取类型为【下载】的文件列表
     *
     * @param pageNum
     * @param pageSize
     * @param zhangHaoId
     * @return
     */
    @Override
    public DtoPageResult<DtoDownloadUploadFile> huoQuDaiXiaZaiWenJianShuByZhangHaoId(Integer pageNum, Integer pageSize, Long zhangHaoId) {
        if(zhangHaoId == null) {
            throw new BusinessException("请提供要查询的账号Id");
        }
        Page<DtoDownloadUploadFile> page = new Page<>(pageNum, pageSize);
        IPage<DtoDownloadUploadFile> dtoDownloadUploadFileIPage = downloadUploadFileMapper.getByZhangHaoIdAndFenLeiAndZhuangTai(page, zhangHaoId, WenJianFenLei.DOWNLOAD, null);

        DtoPageResult<DtoDownloadUploadFile> dtoPageResult = Converter.convertPageResultMybatisPlus(dtoDownloadUploadFileIPage);
        return dtoPageResult;
    }

    /**
     * 根据Id下载文件
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public String xiaZaiWenJianById(Long id) {
        if(id == null) {
            throw new BusinessException("请提供要下载的文件");
        }
        Optional<DownloadUploadFile> downloadUploadFileOptional = downLoadUploadFileRepository.findById(id);
        if(!downloadUploadFileOptional.isPresent()) {
            throw new BusinessException("要下载的文件未找到");
        }
        DownloadUploadFile downloadUploadFile = downloadUploadFileOptional.get();
        downloadUploadFile.setWenJianZhuangTai(WenJianZhuangTai.YI_XIA_ZAI);
        String fileName = downloadUploadFile.getMingCheng();
        String ossFileKey = downloadUploadFile.getOssKey();
        String fileUrl = ossHelper.getFileUrl(ossFileKey);


        // 下载文件到本地
        //ossHelper.downloadFileToLocal(fileLocalPath, ossFileKey);
        //TODO
        // String fileUrl = ossHelper.getBucketFileUrl(ossFileKey);
        return fileUrl;
    }

    private org.springframework.core.io.Resource loadFileAsResource(String localFilePath) {
        try {
            Path filePath = Paths.get(localFilePath).normalize();

            org.springframework.core.io.Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new BusinessException("未找到下载文件");
            }
        } catch (MalformedURLException ex) {
            throw new BusinessException("未找到下载文件");
        }
    }
}
