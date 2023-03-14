package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Article;
import top.vikingar.mapper.ArticleMapper;
import top.vikingar.service.ArticleService;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/14 21:25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 查询条件： 正式文章、降序排序、最多十条
        wrapper.eq(Article::getStatus, 0).orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, wrapper);
        List<Article> articleList = page.getRecords();
        return ResponseResult.okResult(articleList);

    }
}
