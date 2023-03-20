package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.RoleAddDto;
import top.vikingar.domain.dto.RoleChangeStatusDto;
import top.vikingar.domain.dto.RoleUpdateDto;
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

    ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult changeStatus(RoleChangeStatusDto role);

    ResponseResult addRole(RoleAddDto roleAddDto);

    ResponseResult getRoleInfoById(Long id);

    ResponseResult updateRole(RoleUpdateDto roleUpdateDto);

    ResponseResult deleteRole(Long id);

    ResponseResult listAllRole();
}
