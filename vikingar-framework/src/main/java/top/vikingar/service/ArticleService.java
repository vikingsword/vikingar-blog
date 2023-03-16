package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Article;

/**
 * @author vikingar
 * @time 2023/3/14 21:23
 */
public interface ArticleService extends IService<Article> {
    /**
     * get hot article
     * @return res
     */
    ResponseResult getHotArticleList();

    /**
     * getArticleList
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param categoryId categoryId
     * @return res
     */
    ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * getArticleDetail
     * @param id id
     * @return res
     */
    ResponseResult getArticleDetail(Long id);
}
