package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.domain.banjifenlei.BanJiFenLei;
import com.zhizhao.jwgl.jiaowuguanli.domain.biaoqian.BiaoQian;
import com.zhizhao.jwgl.jiaowuguanli.domain.shanchangkemu.ShanChangKeMu;
import com.zhizhao.jwgl.jiaowuguanli.domain.shangkejiaoshi.ShangKeJiaoShi;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BanJiFenLeiMapper;
import com.zhizhao.jwgl.jiaowuguanli.mapper.BiaoQianMapper;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ShanChangKeMuMapper;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ShangKeJiaoShiMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.ShanChangKeMuRepository;
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

@RestController
@RequestMapping("common")
public class CommonController {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    ShanChangKeMuMapper shanChangKeMuMapper;

    @Resource
    BiaoQianMapper biaoQianMapper;

    @Resource
    ShangKeJiaoShiMapper shangKeJiaoShiMapper;

    @Resource
    BanJiFenLeiMapper banJiFenLeiMapper;

    @Autowired
    ShanChangKeMuRepository shanChangKeMuRepository;


    @GetMapping("huoQuShanChangKeMuLieBiao")
    public PPResult huoQuShanChangKeMuLieBiao() {
        List<ShanChangKeMu> shanChangKeMus = shanChangKeMuMapper.selectAll();
        return PPResult.getPPResultOK(shanChangKeMus);
    }

    @Transactional
    @PostMapping("chuangJianShanChangKeMu")
    public PPResult chuangJianShanChangKeMu(@Valid @RequestBody ShanChangKeMu.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);

        ShanChangKeMu shanChangKeMu = ShanChangKeMu.chuangJian(cmd);

        entityManager.persist(shanChangKeMu);
        entityManager.flush();

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);

        return PPResult.getPPResultOK(result);
    }

    /**
     * 获取标签列表
     * @return
     */
    @GetMapping("huoQuBiaoQianLieBiao")
    public PPResult huoQuBiaoQianLieBiao() {
        List<BiaoQian> biaoQians = biaoQianMapper.selectAll();
        return PPResult.getPPResultOK(biaoQians);
    }

    /**
     * 获取上课教室列表
     * @return
     */
    @GetMapping("huoQuShangKeJiaoShiAll")
    public PPResult huoQuShangKeJiaoShiLieBiao() {
        List<ShangKeJiaoShi> shangKeJiaoShiList = shangKeJiaoShiMapper.selectAll();
        return PPResult.getPPResultOK(shangKeJiaoShiList);
    }

    /**
     * 获取班级分类列表
     * @return
     */
    @GetMapping("huoQuBanJiFenLeiAll")
    public PPResult huoQuBanJiFenLeiAll() {
        List<BanJiFenLei> banJiFenLeiList = banJiFenLeiMapper.selectAll();
        return PPResult.getPPResultOK(banJiFenLeiList);
    }
}
