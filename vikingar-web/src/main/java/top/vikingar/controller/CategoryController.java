package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vikingar.domain.ResponseResult;
import top.vikingar.service.CategoryService;

/**
 * @author vikingar
 * @time 2023/3/15 4:57
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类", description = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getCategoryList")
    @ApiOperation(value = "分类列表", notes = "获取分类列表")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
