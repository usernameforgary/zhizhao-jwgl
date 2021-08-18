package com.zhizhao.jwgl.jiaowuguanli.service.oss.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "oss.aliyun")
@Configuration
public class AliyunOssProperties {
    private String endpoint;

    private String accessKeyID;

    private String accessKeySecret;

    private String bucketPublicName;

    private String bucketPublicDomain;
}
