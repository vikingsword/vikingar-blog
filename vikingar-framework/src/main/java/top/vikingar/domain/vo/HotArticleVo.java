package top.vikingar.domain.vo;

import lombok.Data;

/**
 * @author vikingar
 * @time 2023/3/14 23:50
 */
@Data
public class HotArticleVo {

    private Long id;

    private String title;

    private Long viewCount;
}
