package com.zhizhao.jwgl.jiaowuguanli.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zhizhao.jwgl.jiaowuguanli.mapper.instpectors.MyInspector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zhizhao.jwgl.jiaowuguanli.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }

    @Bean
    public MyInspector myInspector() {
        return new MyInspector();
    }
}
