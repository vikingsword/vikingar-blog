package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.UserDto;
import top.vikingar.service.UserService;

/**
 * @author vikingar
 * @time 2023/3/20 16:28
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        return userService.getUserList(pageNum,pageSize,userName,phonenumber,status);
    }

    @PostMapping()
    public ResponseResult add(@RequestBody @Validated UserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUserInfo(@PathVariable("id") Long id) {
        return userService.getUserInfoById(id);
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

}
