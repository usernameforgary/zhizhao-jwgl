package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.MyUserDetails;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ZhangHaoMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.vo.ZhangHaoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("zhanghao")
public class ZhangHaoController {
    @Resource
    ZhangHaoMapper zhangHaoMapper;

    @Autowired
    ZhangHaoRepository zhangHaoRepository;

    // 获取账号信息
    @GetMapping("zhangHaoXinXi")
    public PPResult zhangHaoXinXi(Authentication authentication) {
       MyUserDetails userDetails = ((MyUserDetails) authentication.getPrincipal());
       ZhangHao zhangHao = userDetails.getZhangHao();

       return PPResult.getPPResultOK(null);
    }
    // 员工列表
    @GetMapping("yuanGongLieBiao")
    public PPResult<Page<ZhangHao>> huoQuYuanGongLiebiao(
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
       Page<ZhangHao> page = new Page<>(pageNum, pageSize);

       LambdaQueryWrapper<ZhangHao> zhangHaoLambdaQueryWrapper = Wrappers.lambdaQuery();
       zhangHaoLambdaQueryWrapper.eq(ZhangHao::getIsDeleted, false);
       zhangHaoLambdaQueryWrapper.eq(ZhangHao::getZhangHaoLeiXing, ZhangHaoLeiXing.YUAN_GONG);

       IPage<ZhangHaoVo> pageResult = zhangHaoMapper.yuanGongLieBiao(page, zhangHaoLambdaQueryWrapper);
       Map<String, Object> result = Converter.convertMyBatisPlusPageResult(pageResult);
       return PPResult.getPPResultOK(result);
    }

    @GetMapping("tt")
    public PPResult<org.springframework.data.domain.Page<ZhangHao>> tt (@RequestParam(defaultValue = "20") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNum) {
        // JAP 分页是从0开始，前端是从1开始
        pageNum = pageNum - 1;

        org.springframework.data.domain.Page<ZhangHao> pageResult = zhangHaoRepository.findAllZhangHaoWithPagination(PageRequest.of(pageNum, pageSize));

        Map<String, Object> result = Converter.convertJpaPageResult(pageResult);
        return PPResult.getPPResultOK(result);
    }
}