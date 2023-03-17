package top.vikingar.controller;

import io.swagger.annotations.Api;
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
@Api(tags = "文章")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> test() {
        return articleService.list();
    }

    @GetMapping("hotArticleList")
    public ResponseResult hotArticleList() {
        return articleService.getHotArticleList();
    }

    @GetMapping("articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.getArticleList(pageNum, pageSize, categoryId);
    }

    /**
     * pathVariable("io") 用于获取GetMapping("/{id}")中的 id
     * @param id id
     * @return res
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id")Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }


}
