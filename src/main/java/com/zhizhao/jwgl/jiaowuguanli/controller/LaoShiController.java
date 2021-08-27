package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.zhizhao.jwgl.jiaowuguanli.dto.DtoLaoShi;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoPageResult;
import com.zhizhao.jwgl.jiaowuguanli.mapper.LaoShiMapper;
import com.zhizhao.jwgl.jiaowuguanli.service.LaoShiService;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("laoshi")
public class LaoShiController {
    @Resource
    LaoShiMapper laoShiMapper;

    @Autowired
    LaoShiService laoShiService;

    /**
     * 获取所有老师
     * @return
     */
    @GetMapping("huoQuLaoShiAll")
    public PPResult huoQuLaoShiAll() {
        List<DtoLaoShi> laoShiList = laoShiMapper.huoQuLaoShiAll();
        return PPResult.getPPResultOK(laoShiList);
    }

    /**
     * 获取老师列表
     * @param pageNum
     * @param pageSize
     * @param keyword 关键字【老师姓名 | 手机号】
     * @param zaiZhiZhuangTai 在职状态
     * @return
     */
    @GetMapping("huoQuLaoShiLieBiao")
    public PPResult huoQuLaoShiLieBiao(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "20") Integer pageSize,
                                       @RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) Boolean zaiZhiZhuangTai) {

        DtoPageResult<DtoLaoShi> dtoPageResult = laoShiService.huoQuLaoShiLieBiao(pageNum, pageSize, keyword, zaiZhiZhuangTai);
        return PPResult.getPPResultOK(dtoPageResult);
    }
}
