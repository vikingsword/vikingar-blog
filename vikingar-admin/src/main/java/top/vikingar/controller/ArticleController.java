package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.AddArticleDto;
import top.vikingar.service.ArticleService;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.getArticleList(pageNum, pageSize, categoryId);
    }


}