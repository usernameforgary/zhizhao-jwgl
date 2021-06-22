package com.zhizhao.jwgl.jiaowuguanli.config.security;

import io.jsonwebtoken.lang.Collections;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class MyDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 如果授权规则为空，则默认此URL无授权就能访问
        if(Collections.isEmpty(configAttributes)) { return;}
        // 判断授权规则和当前用户所属权限是否匹配
        for(ConfigAttribute configAttribute: configAttributes) {
            for(GrantedAuthority authority: authentication.getAuthorities()) {
                if(Objects.equals(authority.getAuthority(), configAttribute.getAttribute())) {
                    return;
                }
            }
        }
        // 走到这里就代表没有权限，必须要抛出异常，否则错误处理器捕捉不到
        throw new AccessDeniedException("没有相关权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
