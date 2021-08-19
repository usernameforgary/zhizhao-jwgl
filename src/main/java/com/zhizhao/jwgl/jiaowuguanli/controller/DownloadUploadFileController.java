package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoDownloadUploadFile;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.service.DownloadUploadFileService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("downloaduploadfile")
public class DownloadUploadFileController {
    @Autowired
    DownloadUploadFileService downloadUploadFileService;

    /**
     * 根据账号Id获取待下载的文件数
     * @param zhangHaoId
     * @return
     */
    @GetMapping("huoQuDaiXiaZaiWenJianShuByZhangHaoId")
    public PPResult huoQuDaiXiaZaiWenJianShuByZhangHaoId(@RequestParam Long zhangHaoId) {
        Integer count = downloadUploadFileService.huoQuDaiXiaZaiWenJianShuLiangById(zhangHaoId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", count);
        return PPResult.getPPResultOK(resultMap);
    }

    /**
     * 根据账号Id，分页获取类型为【下载】的文件列表
     * @param pageNum
     * @param pageSize
     * @param zhangHaoId
     * @return
     */
    @GetMapping("huoQuXiaZaiWenJianByZhangHaoId")
    public PPResult huoQuXiaZaiWenJianByZhangHaoId(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam Long zhangHaoId
    ) {
        DtoPageResult<DtoDownloadUploadFile> dtoPageResult = downloadUploadFileService.huoQuDaiXiaZaiWenJianShuByZhangHaoId(pageNum, pageSize, zhangHaoId);
        return PPResult.getPPResultOK(dtoPageResult);
    }

    /**
     * 根据Id下载文件
     * @param id 文件信息Id
     * @return
     */
    @GetMapping("xiaZaiWenJianById")
    public PPResult xiaZaiWenJianById(@RequestParam Long id, HttpServletRequest request) {
        String fileUrl = downloadUploadFileService.xiaZaiWenJianById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("url", fileUrl);
        return PPResult.getPPResultOK(result);
    }
}
