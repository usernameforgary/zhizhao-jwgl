package com.zhizhao.jwgl.jiaowuguanli.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String hostName;
    private Integer port;
    private Long connTimeout;
    private Integer maxActive;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxWait;
    private Boolean ssl;
}
