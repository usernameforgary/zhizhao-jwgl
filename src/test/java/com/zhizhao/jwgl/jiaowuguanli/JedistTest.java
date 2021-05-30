package com.zhizhao.jwgl.jiaowuguanli;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedistTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSave() {
        redisTemplate.boundSetOps("mykey3").add("hello redis 123");
    }
}
