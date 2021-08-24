package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.constant.ZhangHaoLeiXing;
import com.zhizhao.jwgl.jiaowuguanli.domain.xitongcaidan.XiTongCaiDan;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.MyUserDetails;
import com.zhizhao.jwgl.jiaowuguanli.domain.zhanghao.ZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.dto.DtoZhangHao;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ZhangHaoMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.ZhangHaoRepository;
import com.zhizhao.jwgl.jiaowuguanli.service.XiTongCaiDanService;
import com.zhizhao.jwgl.jiaowuguanli.service.ZhangHaoService;
import com.zhizhao.jwgl.jiaowuguanli.utils.Converter;
import com.zhizhao.jwgl.jiaowuguanli.utils.PPResult;
import com.zhizhao.jwgl.jiaowuguanli.vo.ZhangHaoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("zhanghao")
public class ZhangHaoController {
    @Resource
    ZhangHaoMapper zhangHaoMapper;

    @Autowired
    ZhangHaoRepository zhangHaoRepository;

    @Autowired
    XiTongCaiDanService xiTongCaiDanService;

    @Autowired
    ZhangHaoService zhangHaoService;

    // 获取账号信息
    @GetMapping("zhangHaoXinXi")
    public PPResult zhangHaoXinXi(Authentication authentication) {
        MyUserDetails userDetails = ((MyUserDetails) authentication.getPrincipal());
        ZhangHao zhangHao = userDetails.getZhangHao();
        Long id = zhangHao.getId();
        // 获取账号的系统菜单
        List<XiTongCaiDan> xiTongCaiDanList = xiTongCaiDanService.huoQuXiTongCanDanByZhangHaoId(id);

        ZhangHaoVo zhangHaoVo = new ZhangHaoVo();
        zhangHaoVo.setXingMing(zhangHao.getXingMing());
        zhangHaoVo.setId(id);
        zhangHaoVo.setIsLaoShi(zhangHao.getIsLaoShi());
        zhangHaoVo.setXitongCaiDanZu(xiTongCaiDanList);

        return PPResult.getPPResultOK(zhangHaoVo);
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

    /**
     * 根据账号类型【员工|学员】获取系统所有账号信息
     * @param zhangHaoLeiXing
     * @return
     */
    @GetMapping("getZhangHaoLeiBiaoByZhangHaoLeiXing")
    public PPResult getZhangHaoLeiBiaoByZhangHaoLeiXing(@RequestParam ZhangHaoLeiXing zhangHaoLeiXing) {
        List<ZhangHao> zhangHaoList = zhangHaoService.getZhangHaoByLeiXing(zhangHaoLeiXing);
        return PPResult.getPPResultOK(zhangHaoList);
    }

    /**
     * 获取所有员工
     * @return
     */
    @GetMapping("houQuYuanGongAll")
    public PPResult houQuYuanGongAll() {
        List<DtoZhangHao> zhangHaoList = zhangHaoService.houQuYuanGongAll();
        return PPResult.getPPResultOK(zhangHaoList);
    }
}