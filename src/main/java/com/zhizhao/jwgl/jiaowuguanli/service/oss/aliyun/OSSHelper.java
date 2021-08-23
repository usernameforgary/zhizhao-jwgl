package com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.gson.Gson;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoOssSignature;
import com.zhizhao.jwgl.jiaowuguanli.exception.BusinessException;
import com.zhizhao.jwgl.jiaowuguanli.service.oss.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class OSSHelper {
    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    public void uploadLocalFile(File localFile, String bucketFileKey) throws Exception {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = aliyunOssProperties.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = aliyunOssProperties.getAccessKeyID();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String bucketName = aliyunOssProperties.getBucketPublicName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 判断存储空间是否存在
        boolean bucketExists = ossClient.doesBucketExist(bucketName);
        if(!bucketExists) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }

        InputStream inputStream = new FileInputStream(localFile);

        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(aliyunOssProperties.getBucketPublicName(), bucketFileKey, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public void downloadFileToLocal(String localPath, String bucketFileKey) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = aliyunOssProperties.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = aliyunOssProperties.getAccessKeyID();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String bucketName = aliyunOssProperties.getBucketPublicName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
        // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, bucketFileKey), new File(localPath));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 返回OSS配置信息
     * @return
     */
    public AliyunOssProperties getOssProperties() {
        return aliyunOssProperties;
    }

    /**
     * 获取文件的下载链接
     * @param bucketFileKey
     * @return
     */
    public URL getObjectDownloadUrl(String bucketFileKey) {
        URL downloadUrl = null;

        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = aliyunOssProperties.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = aliyunOssProperties.getAccessKeyID();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String bucketName = aliyunOssProperties.getBucketPublicName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);

        downloadUrl = ossClient.generatePresignedUrl(bucketName, bucketFileKey, expiration);

        return downloadUrl;
    }

    /**
     * 获取上传文件
     * @param bucketFileKey
     * @return
     */
    public DtoOssSignature getPutObjectUrlPolicy(String bucketFileKey) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = aliyunOssProperties.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = aliyunOssProperties.getAccessKeyID();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String bucketName = aliyunOssProperties.getBucketPublicName();

        String host = "https://" + bucketName + '.' + endpoint;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 保存到OSS中的文件路径（包含文件名）
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/" + bucketFileKey;

        DtoOssSignature dtoOssSignature = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            dtoOssSignature = new DtoOssSignature();
            dtoOssSignature.setAccessid(accessKeyId);
            dtoOssSignature.setPolicy(encodedPolicy);
            dtoOssSignature.setSignature(postSignature);
            dtoOssSignature.setDir(dir);
            dtoOssSignature.setHost(host);
            dtoOssSignature.setExpire(String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BusinessException("获取文件上传信息失败");
        } finally {
            ossClient.shutdown();
            return dtoOssSignature;
        }

    }
}
