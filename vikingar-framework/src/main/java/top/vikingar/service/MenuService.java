package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 15:06:54
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);
}
