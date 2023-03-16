package top.vikingar.utils;

import org.springframework.beans.BeanUtils;
import top.vikingar.domain.entity.Article;
import top.vikingar.domain.vo.HotArticleVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vikingar
 * @time 2023/3/15 0:05
 */
public class BeanCopyUtils {

    /**
     * bean conversion
     * 按照相同参数名进行转化，要求类型也相同
     * @param source source
     * @param clazz clazz
     * @return res
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <V,O> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("ab");
        article.setViewCount(100L);
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);
    }
}
