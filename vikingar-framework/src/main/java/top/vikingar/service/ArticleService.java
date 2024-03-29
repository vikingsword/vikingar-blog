package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.AddArticleDto;
import top.vikingar.domain.entity.Article;
import top.vikingar.domain.vo.ArticleTagVo;

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

    /**
     * updateViewCount
     * @param id id
     * @return res
     */
    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);


    ResponseResult getArticleList2(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult getArticleById(Long id);

    ResponseResult updateArticle(ArticleTagVo articleTagInfo);

    ResponseResult deleteArticle(Long id);

}
