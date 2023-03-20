package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.RoleAddDto;
import top.vikingar.domain.dto.RoleChangeStatusDto;
import top.vikingar.domain.dto.RoleUpdateDto;
import top.vikingar.domain.entity.Role;
import top.vikingar.domain.entity.RoleMenu;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.domain.vo.RoleInfoVo;
import top.vikingar.mapper.RoleMapper;
import top.vikingar.service.RoleMenuService;
import top.vikingar.service.RoleService;
import top.vikingar.utils.BeanCopyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 15:11:35
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(roleName), Role::getRoleName, roleName);
        wrapper.eq(Objects.nonNull(status), Role::getStatus, status);

        Page<Role> page = new Page<>(pageNum, pageSize);
        page(page,wrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleChangeStatusDto role) {
        Role roleEntity = getById(role.getRoleId());
        roleEntity.setStatus(role.getStatus());
        updateById(roleEntity);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addRole(RoleAddDto roleAddDto) {
        Role role = BeanCopyUtils.copyBean(roleAddDto, Role.class);
        save(role);
        List<String> menuIds = roleAddDto.getMenuIds();
        menuIds.stream().map(m -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(Long.valueOf(m));
            return roleMenuService.save(roleMenu);
        }).collect(Collectors.toList());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRoleInfoById(Long id) {
        Role role = getById(id);
        RoleInfoVo roleInfoVo = BeanCopyUtils.copyBean(role, RoleInfoVo.class);
        return ResponseResult.okResult(roleInfoVo);
    }

    @Override
    @Transactional
    public ResponseResult updateRole(RoleUpdateDto roleUpdateDto) {
        Role role = BeanCopyUtils.copyBean(roleUpdateDto, Role.class);
        updateById(role);

        // 更新menu：先删除后插入
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId,roleUpdateDto.getId()));
        roleUpdateDto.getMenuIds().stream().map(rm -> {
            RoleMenu roleMenu = new RoleMenu(roleUpdateDto.getId(), Long.valueOf(rm));
            return roleMenuService.save(roleMenu);
        }).collect(Collectors.toList());

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(Long id) {
        roleMapper.deleteById(id);
        return ResponseResult.okResult();
    }

}
