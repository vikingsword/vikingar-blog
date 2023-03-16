package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-15 04:39:24
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
