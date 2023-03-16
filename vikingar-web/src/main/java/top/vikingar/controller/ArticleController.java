package top.vikingar.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseResult hotArticleList(){
        return articleService.getHotArticleList();
    }
}
