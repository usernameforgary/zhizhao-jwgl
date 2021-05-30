package com.zhizhao.jwgl.jiaowuguanli.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //@Bean
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
}
