package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.constant.ErrorCode;
import com.zhizhao.jwgl.jiaowuguanli.domain.juese.JueSe;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoTianJiaYuanGong;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoZhanghaoDengLu;
import com.zhizhao.jwgl.jiaowuguanli.mapper.JueSeMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.JueSeRepository;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.service.YuanGongService;
import com.zhizhao.jwgl.jiaowuguanli.utils.JwtUtil;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    YuanGongService yuanGongService;
    @Autowired
    ZhangHaoRepository zhangHaoRepository;
    @Autowired
    JueSeRepository jueSeRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/dengLu")
    public PPResult dengLu(@Valid @RequestBody DtoZhanghaoDengLu dto) {
        Optional<ZhangHao> optionalZhangHao = zhangHaoRepository.findByShouJi(dto.getShouJi());
        if(!(optionalZhangHao.isPresent())) {
            return PPResult.fail(ErrorCode.DataNotFound, "用户名或密码错误!");
        }
        if(bCryptPasswordEncoder.matches(dto.getMiMa(), optionalZhangHao.get().getMiMa())) {
            String token = jwtUtil.generateToken(optionalZhangHao.get());

            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("token", token);

            return PPResult.getPPResultOK(map);
        }

        return PPResult.fail(ErrorCode.DataNotFound, "用户名或密码错误!");
    }

    // 添加员工
    @PostMapping("tianJianYuanGong")
    public PPResult tianJiaYuanGong(@Valid @RequestBody DtoTianJiaYuanGong dto) {
        Long id = SnowflakeIdUtil.nextId();
        dto.setId(id);
        yuanGongService.tianJianYuanGong(dto);
        return PPResult.Ok();
    }

    @GetMapping("ceshi")
    public String ceShiShanChu() {
        Optional<JueSe> optionalJueSe = jueSeRepository.findById(1L);
        if(optionalJueSe.isPresent()) {
            JueSe jueSe = optionalJueSe.get();
            jueSeRepository.delete(jueSe);
        }
        return "Success";
    }
}
