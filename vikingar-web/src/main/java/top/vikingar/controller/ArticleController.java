package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Article;
import top.vikingar.service.ArticleService;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/14 21:30
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章", description = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("hotArticleList")
    @ApiOperation(value = "热门文章列表", notes = "获取热门文章")
    public ResponseResult hotArticleList() {
        return articleService.getHotArticleList();
    }

    @GetMapping("articleList")
    @ApiOperation(value = "文章列表", notes = "分页获取文章")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.getArticleList(pageNum, pageSize, categoryId);
    }

    /**
     * pathVariable("io") 用于获取GetMapping("/{id}")中的 id
     *
     * @param id id
     * @return res
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "文章详情", notes = "获取文章详情信息")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("updateViewCount/{id}")
    @ApiOperation(value = "更新浏览量", notes = "从redis中获取浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }


}
