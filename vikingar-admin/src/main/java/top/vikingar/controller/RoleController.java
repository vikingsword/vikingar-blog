package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.RoleAddDto;
import top.vikingar.domain.dto.RoleChangeStatusDto;
import top.vikingar.domain.dto.RoleUpdateDto;
import top.vikingar.service.RoleService;

/**
 * @author vikingar
 * @time 2023/3/20 11:01
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.getRoleList(pageNum, pageSize, roleName, status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleChangeStatusDto roleChangeStatusDto) {
        return roleService.changeStatus(roleChangeStatusDto);
    }

    @PostMapping
    public ResponseResult add(@RequestBody RoleAddDto roleAddDto) {
        return roleService.addRole(roleAddDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRoleInfo(@PathVariable("id") Long id) {
        return roleService.getRoleInfoById(id);
    }

    @PutMapping()
    public ResponseResult update(@RequestBody RoleUpdateDto roleUpdateDto) {
        return roleService.updateRole(roleUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole() {
        return roleService.listAllRole();
    }
}
