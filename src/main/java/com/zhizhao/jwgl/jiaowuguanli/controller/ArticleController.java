package com.zhizhao.jwgl.jiaowuguanli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhizhao.jwgl.jiaowuguanli.domain.article.ArticleView;
import com.zhizhao.jwgl.jiaowuguanli.dto.ArticleDto;
import com.zhizhao.jwgl.jiaowuguanli.mapper.ArticleMapper;
import com.zhizhao.jwgl.jiaowuguanli.repository.ArticleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @PostMapping("/saveArticle")
    public void saveArticle(@RequestBody ArticleDto dto) {
        ArticleView articleView = new ArticleView();
        articleView.setTitle(dto.getTitle());

        articleJpaRepository.save(articleView);
    }

    @GetMapping("/getArticle")
    public void getArticle(@RequestParam String id ) {
        ArticleView articleView = articleMapper.selectById(1);
        System.out.println(articleView.getTitle());
    }

    @GetMapping("/getAllArticle")
    public IPage<ArticleView> getAllArticle() {
        Page page = new Page(1, 2);
        return articleMapper.selectAll(page);
    }
}
