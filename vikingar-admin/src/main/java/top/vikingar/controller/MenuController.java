package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Menu;
import top.vikingar.service.MenuService;

/**
 * @author vikingar
 * @time 2023/3/20 9:54
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult getMenuList(String menuName, String status) {
        return menuService.getMenuList(status, menuName);
    }

    @PostMapping()
    public ResponseResult add(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenuInfo(@PathVariable("id") Long id) {
        return menuService.getMenuInfoById(id);
    }

    @PutMapping()
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable("menuId") Long id) {
        return menuService.deleteMenu(id);
    }

}
