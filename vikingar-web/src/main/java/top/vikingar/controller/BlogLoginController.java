package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;
import top.vikingar.service.BlogLoginService;

import javax.validation.Valid;

/**
 * @author vikingar
 * @time 2023/3/17 14:33
 */
@RestController
@Api(tags = "登陆", description = "登陆相关接口")
public class BlogLoginController {


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
     *
     * @param user user
     * @return res
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResponseResult login(@RequestBody @Validated User user) {
//        if (!StringUtils.hasText(user.getUserName())) {
//            // 用户名校验
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
