package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.annotation.SystemLog;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;
import top.vikingar.service.UserService;

/**
 * @author vikingar
 * ctrl + alt + O 去掉无用的包
 * @time 2023/3/16 23:55
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(BusinessName = "更新用户信息")
    @ApiOperation(value = "用户个人信息", notes = "获取登陆后的用户个人信息")
    public ResponseResult userInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation(value = "更新用户信息", notes = "登陆后编辑用户个人信息")
    public ResponseResult updateUser(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
