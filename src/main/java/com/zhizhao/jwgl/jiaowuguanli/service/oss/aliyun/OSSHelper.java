package com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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

}
