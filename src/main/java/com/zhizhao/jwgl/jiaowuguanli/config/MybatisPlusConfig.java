package com.zhizhao.jwgl.jiaowuguanli.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.zhizhao.jwgl.jiaowuguanli.mapper.instpectors.MyInspector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zhizhao.jwgl.jiaowuguanli.mapper")
public class MybatisPlusConfig {

    // TODO 分页配置写到这里不起作用，需要写到启动类
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
//        paginationInnerInterceptor.setDbType(DbType.MYSQL);
//        //paginationInnerInterceptor.setDialect(new DialectModel("mysql"));
//        //paginationInnerInterceptor.setOptimizeJoin(true);
//        return paginationInnerInterceptor;
//    }

    @Bean
    public MyInspector myInspector() {
        return new MyInspector();
    }
}
