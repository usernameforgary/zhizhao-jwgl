package com.zhizhao.jwgl.jiaowuguanli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
//    @Value("${redis.hostName}")
//    private String redisHostName;
//
//    @Value("${redis.port}")
//    private int redisPort;
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getMaxActive());
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        poolConfig.setMinIdle(redisProperties.getMinIdle());
        JedisClientConfiguration jedisClientConfiguration = null;

        if(redisProperties.getSsl()) {
            jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
                    .poolConfig(poolConfig).and()
                    .readTimeout(Duration.ofMillis(redisProperties.getConnTimeout()))
                    .useSsl()
                    .build();
        } else {
            jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
                    .poolConfig(poolConfig).and()
                    .readTimeout(Duration.ofMillis(redisProperties.getConnTimeout()))
                    .build();
        }

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHostName());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
