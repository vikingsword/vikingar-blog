package top.vikingar.controller;

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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(BusinessName = "更新用户信息")
    public ResponseResult userInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUser(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
