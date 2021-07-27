package com.zhizhao.jwgl.jiaowuguanli.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    private static final String FILTER_STAT_PREFIX = "spring.datasource.druid.filter.stat";

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druid() {
//        return new DruidDataSource();
//    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        String urlPattern = "/druid/*";

        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(statViewServlet, urlPattern);

        Map<String, String> initParams = new HashMap<>();
        initParams.put("resetEnable", "false");
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        initParams.put("allow", "127.0.0.1");

        bean.setInitParameters(initParams);

        return bean;
    }

    @Bean
    @ConfigurationProperties(FILTER_STAT_PREFIX)
    @ConditionalOnProperty(prefix = FILTER_STAT_PREFIX, name = "enabled")
    public StatFilter statFilter() {
        return new StatFilter();
    }
}
