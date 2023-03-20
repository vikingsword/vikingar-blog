package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.vikingar.domain.entity.Article;
import top.vikingar.domain.entity.Tag;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/19 10:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagVo {

    private Article article;

    private List<Long> tagList;

}
