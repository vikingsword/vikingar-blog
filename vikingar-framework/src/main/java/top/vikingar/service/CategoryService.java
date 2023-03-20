package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.CategoryAddDto;
import top.vikingar.domain.entity.Category;
import top.vikingar.domain.vo.CategoryPageVo;
import top.vikingar.domain.vo.CategoryVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-03-15 04:39:24
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult getCategoryList(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult add(CategoryAddDto categoryAddDto);

    ResponseResult updateGetInfo(Long id);

    ResponseResult updateCategory(CategoryPageVo categoryDto);

    ResponseResult deleteCategory(Long id);
}
