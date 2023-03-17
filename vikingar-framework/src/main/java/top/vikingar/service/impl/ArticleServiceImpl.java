package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vikingar.constants.SystemConstants;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Article;
import top.vikingar.domain.entity.Category;
import top.vikingar.domain.vo.ArticleDetailVo;
import top.vikingar.domain.vo.ArticleListVo;
import top.vikingar.domain.vo.HotArticleVo;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.mapper.ArticleMapper;
import top.vikingar.service.ArticleService;
import top.vikingar.service.CategoryService;
import top.vikingar.utils.BeanCopyUtils;
import top.vikingar.utils.RedisCache;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author vikingar
 * @time 2023/3/14 21:25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult getHotArticleList() {

        // 查询条件： 正式文章、降序排序、最多十条
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, wrapper);
        List<Article> articleList = page.getRecords();
        // Integer --> Long : Long.valueOf((Integer).toString())
        articleList.stream()
                .map(article -> article.setViewCount(Long.valueOf((redisCache.getCacheMapValue("article:viewCount", article.getId().toString())).toString())))
                .collect(Collectors.toList());

        // bean 拷贝
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);

    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {

        // 查询条件
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);

        // 状态是正式发布的
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        // 置顶的文章显示在最前面,对isTop进行降序
        wrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);

        // 从 redis 中查询文章viewCount
        // 查询分类名称 链式编程
        List<Article> articles = page.getRecords();
        articles.stream()
                .map(article -> {
                    article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                    article.setViewCount(Long.valueOf((redisCache.getCacheMapValue("article:viewCount",article.getId().toString())).toString()));
                    return article;
                })
                .collect(Collectors.toList());

        // 封装结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {

        // 根据id查文章
        Article article = getById(id);
        //从 redis 中查询文章viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        // 封装Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类id查分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);

    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }
}
