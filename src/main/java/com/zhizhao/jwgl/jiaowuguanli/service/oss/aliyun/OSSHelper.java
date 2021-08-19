package com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.gson.Gson;
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

    //TODO 临时用公共的
    public String getFileUrl(String bucketFileKey) {
        return aliyunOssProperties.getBucketPublicDomain() + bucketFileKey;
    }

    public String getBucketFileUrl(String bucketFileKey) {
        String accessKeyId = aliyunOssProperties.getAccessKeyID();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        //构建一个阿里云客户端，用于发起请求。
        //构建阿里云客户端时需要设置AccessKey ID和AccessKey Secret。
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        //构造请求，设置参数。关于参数含义和设置方法，请参见API参考。
        AssumeRoleRequest request = new AssumeRoleRequest();

        request.setRoleArn("acs:ram::1539926392555905:role/dev");
        request.setRoleSessionName("alice");

        String token = null;
        //发起请求，并得到响应。
        try {
            AssumeRoleResponse response = client.getAcsResponse(request);
            token = response.getCredentials().getSecurityToken();
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return token;
    }
}
