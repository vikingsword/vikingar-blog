package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vikingar.constants.SystemConstants;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Article;
import top.vikingar.domain.entity.Category;
import top.vikingar.domain.vo.CategoryVo;
import top.vikingar.mapper.CategoryMapper;
import top.vikingar.service.ArticleService;
import top.vikingar.service.CategoryService;
import top.vikingar.utils.BeanCopyUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-03-15 04:39:25
 */


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //获取文章的分类id，并且去重
        List<Long> idList = articleList.stream().map(Article::getCategoryId).distinct().collect(Collectors.toList());

        //查询分类表, 过滤非正常状态
        List<Category> categories = listByIds(idList);
        List<Category> list = categories.stream().filter(o -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(o.getStatus())).collect(Collectors.toList());

        // bean转化
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);

    }


}
