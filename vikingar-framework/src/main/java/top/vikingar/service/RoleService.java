package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 15:11:35
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
