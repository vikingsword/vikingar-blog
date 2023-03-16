package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;
import top.vikingar.service.BlogLoginService;

/**
 * @author vikingar
 * @time 2023/3/16 23:55
 */
@RestController
public class UserController {

    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * 登录
     * ①自定义登录接口
     * 调用ProviderManager的方法进行认证 如果认证通过生成jwt
     * 把用户信息存入redis中
     * ②自定义UserDetailsService
     * 在这个实现类中去查询数据库
     * 注意配置passwordEncoder为BCryptPasswordEncoder
     * @param user user
     * @return res
     */
    @PostMapping("login")
    public ResponseResult login(@RequestBody User user) {
        return blogLoginService.login(user);
    }
}
