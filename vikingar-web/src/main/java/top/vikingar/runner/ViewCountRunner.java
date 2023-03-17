package top.vikingar.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.vikingar.domain.entity.Article;
import top.vikingar.mapper.ArticleMapper;
import top.vikingar.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vikingar
 * @time 2023/3/17 20:18
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> map = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article:viewCount", map);
    }
}
