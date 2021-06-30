package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhizhao.jwgl.jiaowuguanli.domain.juese.JueSe;
import com.zhizhao.jwgl.jiaowuguanli.mapper.JueSeMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.JueSeRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("jueSe")
public class JueSeController {
    @PersistenceContext
    EntityManager entityManager;

    @Resource
    JueSeMapper jueSeMapper;

    @GetMapping("huoQuJueseLieBiao")
    public PPResult<JueSe> huoQuJueseLieBiao() {
//        QueryWrapper<JueSe> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(JueSe::getIsDeleted, false);
        List<JueSe> jueSes = jueSeMapper.selectAll();
        return PPResult.getPPResultOK(jueSes);
    }

    @PostMapping("chuangJianJueSe")
    public PPResult xinJianJueSe(@Valid @RequestBody JueSe.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);

        JueSe jueSe = JueSe.chuangJian(cmd);

        entityManager.persist(jueSe);
        entityManager.flush();

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return PPResult.getPPResultOK(result);
    }
}
