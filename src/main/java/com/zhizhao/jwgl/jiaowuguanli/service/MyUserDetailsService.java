package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.MyUserDetails;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ZhangHaoRepository zhangHaoRepository;

    @Resource
    MyRBACService myRBACService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取账户基本信息
        ZhangHao zhangHao = zhangHaoRepository.getUserByUsername(username);
        if(zhangHao == null) {
            throw new UsernameNotFoundException("对应账户未找到");
        }
        MyUserDetails myUserDetails = new MyUserDetails(zhangHao);

        Set<String> autorities = new HashSet<>();
        //获取账号所拥有的，系统API的url信息
        List<String> xiTongApis = myRBACService.findApiByZhangHuId(zhangHao.getId());
        autorities.addAll(xiTongApis);

        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", autorities)
                )
        );

        return myUserDetails;
    }
}
