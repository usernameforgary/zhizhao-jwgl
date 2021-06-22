package com.zhizhao.jwgl.jiaowuguanli.config.security;

import com.zhizhao.jwgl.jiaowuguanli.repository.XiTongApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
public class MySecurityMetadataSource implements SecurityMetadataSource {
    @Autowired
    XiTongApiRepository xiTongApiRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        // 该对象是Spring Security帮我们封装好的，可以通过该对象获取request等信息
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        //TODO 这里涉及数据库查询，每个请求会执行这里，需要优化
        //获取系统api的url
        Set<String> urls = xiTongApiRepository.getAllApiUrl();
        // 遍历所有权限资源，以和当前请求所需的权限进行匹配
        for (String url: urls) {
            // 因为/API/user/test/{id}这种路径参数不能直接equals来判断请求路径是否匹配，所以需要用Ant类来匹配
            AntPathRequestMatcher ant = new AntPathRequestMatcher(url);
            // 如果请求方法和请求路径都匹配上了，则代表找到了这个请求所需的权限资源
            //if (request.getMethod().equals(url) && ant.matches(request)) {
            if (ant.matches(request)) {
                // 将我们权限资源返回
                return Collections.singletonList(new SecurityConfig(url));
            }
        }
        // 走到这里就代表该请求无需授权即可访问，返回空
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
