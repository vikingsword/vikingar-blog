package top.vikingar.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vikingar
 * @time 2023/3/19 10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDetailVo {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;

    /**
     * 访问量
     */
    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;


    private Date createTime;


}
