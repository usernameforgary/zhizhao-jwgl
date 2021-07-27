package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.constant.ErrorCode;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoZhanghaoDengLu;
import com.zhizhao.jwgl.jiaowuguanli.repository.JueSeRepository;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.service.ZhangHaoService;
import com.zhizhao.jwgl.jiaowuguanli.utils.JwtUtil;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("public")
@Transactional
public class PublicController {
    @Autowired
    ZhangHaoService zhangHaoService;
    @Autowired
    YuanGongService yuanGongService;
    @Autowired
    ZhangHaoRepository zhangHaoRepository;
    @Autowired
    JueSeRepository jueSeRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    DataSource dataSource;

    @PostMapping("/dengLu")
    public PPResult dengLu(@Valid @RequestBody DtoZhanghaoDengLu dto) {
        ZhangHao zhangHao = zhangHaoService.getZhangHaoByShouJiAndLeiXing(dto.getShouJi(), dto.getZhangHaoLeiXing());
        if(zhangHao == null) {
            return PPResult.fail(ErrorCode.DataNotFound, "用户名或密码错误!");
        }
        if(bCryptPasswordEncoder.matches(dto.getMiMa(), zhangHao.getMiMa())) {
            String token = jwtUtil.generateToken(zhangHao);

            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("token", token);

            return PPResult.getPPResultOK(map);
        }

        return PPResult.fail(ErrorCode.NoAuthorization, "用户名或密码错误!");
    }

    @GetMapping("hello")
    public String hello() {
        System.out.println(dataSource);
        return "hello world";
    }
}
