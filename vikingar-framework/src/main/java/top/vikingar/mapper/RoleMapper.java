package top.vikingar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vikingar.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-18 15:11:35
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
