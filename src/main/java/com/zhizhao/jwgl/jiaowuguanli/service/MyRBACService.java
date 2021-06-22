package com.zhizhao.jwgl.jiaowuguanli.service;

import com.zhizhao.jwgl.jiaowuguanli.mapper.MyUserDetailsServiceMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyRBACService {
    @Resource
    MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    List<String> findApiByZhangHuId(Long zhangHuId) {
        return myUserDetailsServiceMapper.findApiByZhangHuId(zhangHuId);
    }
}
