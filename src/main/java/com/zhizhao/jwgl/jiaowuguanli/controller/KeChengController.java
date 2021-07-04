package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.kecheng.KeCheng;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoKeCheng;
import com.zhizhao.jwgl.jiaowuguanli.mapper.KeChengMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.KeChengRespository;
import com.zhizhao.jwgl.jiaowuguanli.service.KeChengService;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("kecheng")
public class KeChengController {
    @Autowired
    KeChengRespository keChengRespository;

    @Autowired
    KeChengService keChengService;

    @Resource
    KeChengMapper keChengMapper;

    /**
     * 新建课程
     * @param cmd
     * @return
     */
    @Transactional
    @PostMapping("xinJianKeCheng")
    public PPResult xinJianKeCheng(@Valid @RequestBody KeCheng.ChuangJianCmd cmd) {
        Long id = SnowflakeIdUtil.nextId();
        cmd.setId(id);
        KeCheng result = KeCheng.chuangJian(cmd);
        keChengRespository.save(result);
        return PPResult.Ok();
    }

    /**
     * 获取课程列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("huoQuKeChengLieBiao")
    public PPResult huoQuKeChengLieBiao(
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {

        IPage<DtoKeCheng> pageResult = keChengService.huoQuKeChengLieBiao(pageNum, pageSize);

        Map<String, Object> result = Converter.convertMyBatisPlusPageResult(pageResult);
        return PPResult.getPPResultOK(result);
    }

    /**
     * 获取所有课程
     * @return
     */
    @GetMapping("huoQuKeChengAll")
    public PPResult huoQuKeChengAll() {
        List<KeCheng> keChengList = keChengMapper.selectAll();
        return PPResult.getPPResultOK(keChengList);
    }
}
