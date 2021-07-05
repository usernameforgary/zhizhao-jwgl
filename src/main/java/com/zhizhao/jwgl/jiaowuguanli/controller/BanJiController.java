package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banji")
public class BanJiController {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @PostMapping("chuangJianBanJi")
    public PPResult chuangJianBanJi(@Valid @RequestBody BanJi.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);
        BanJi banJi = BanJi.chuangJian(cmd);

        entityManager.persist(banJi);
        entityManager.flush();

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);

        return PPResult.getPPResultOK(result);
    }

    @GetMapping("huoQuBanJiLeiBiao")
    public PPResult huoQuBanJiLeiBiao(
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        //IPage<DtoBanJi> pageResult =
        return PPResult.Ok();
    }
}
