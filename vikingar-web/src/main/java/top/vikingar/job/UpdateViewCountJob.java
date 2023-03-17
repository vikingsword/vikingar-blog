package top.vikingar.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.vikingar.domain.entity.Article;
import top.vikingar.service.ArticleService;
import top.vikingar.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vikingar
 * @time 2023/3/17 20:48
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/30 * * * * ?")
    public void updateViewCount() {

        // 获取redis中的浏览量
        Map<String, Integer> map = redisCache.getCacheMap("article:viewCount");
        // 双列流无法直接转化为stream对象，所以要转化为单列集合
        List<Article> list = map.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库中
        articleService.updateBatchById(list);

    }
}
