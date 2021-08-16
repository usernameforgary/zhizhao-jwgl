package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhizhao.jwgl.jiaowuguanli.domain.banji.BanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoBanJiXueYuan;
import com.zhizhao.jwgl.jiaowuguanli.service.BanJiService;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banji")
public class BanJiController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BanJiService banJiService;

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

    /**
     * 获取班级列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("huoQuBanJiLeiBiao")
    public PPResult huoQuBanJiLeiBiao(
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {

        IPage<DtoBanJi> pageResult = banJiService.huoQuBanJiLieBiao(pageNum, pageSize);

        Map<String, Object> result = Converter.convertMyBatisPlusPageResult(pageResult);

        return PPResult.getPPResultOK(result);
    }

    /**
     * 根据id，获取班级基础信息
     * @param id
     * @return
     */
    @GetMapping("huoQuBanJiXiangQing")
    public PPResult huoQuBanJiXiangQing(@RequestParam Long id) {
        DtoBanJi dtoBanJi = banJiService.huoQuBanJiXiangQing(id);
        return PPResult.getPPResultOK(dtoBanJi);
    }

    /**
     * 根据课程Id，获取选择了该课程的所有班级
     * @param keChengId
     * @return
     */
    @GetMapping("huoQuBanJiLieBiaoByKeChengId")
    public PPResult huoQuBanJiLieBiaoByKeChengId(@RequestParam Long keChengId) {
        List<DtoBanJi> dtoBanJiList = banJiService.huoQuBanJiByKeChengId(keChengId);
        return PPResult.getPPResultOK(dtoBanJiList);
    }

    /**
     * 根据班级id，获取班级学员
     * @param banJiId 班级Id
     * @return
     */
    @GetMapping("houQuBanJiXueYuanByBanJiId")
    public PPResult houQuBanJiXueYuanByBanJiId(@RequestParam Long banJiId) {
        List<DtoBanJiXueYuan> dtoBanJiXueYuanList = banJiService.huoQuBanJiXueYuanByBanJiId(banJiId);
        return PPResult.getPPResultOK(dtoBanJiXueYuanList);
    }

    /**
     * 根据学员Id，获取班级列表
     * @param xueYuanId 学员Id
     * @return
     */
    @GetMapping("huoQuBanJiLieBiaoByXueYuanId")
    public PPResult huoQuBanJiLieBiaoByXueYuanId(@RequestParam Long xueYuanId) {
        List<DtoBanJi> dtoBanJiList = banJiService.huoQuBanJiByXueYuanId(xueYuanId);
        return PPResult.getPPResultOK(dtoBanJiList);
    }
}
