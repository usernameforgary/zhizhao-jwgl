package com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {
    private ZhangHao zhangHao;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(ZhangHao zhangHao) {
        this.zhangHao = zhangHao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return zhangHao.getMiMa();
    }

    @Override
    public String getUsername() {
        return zhangHao.getShouJi();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
