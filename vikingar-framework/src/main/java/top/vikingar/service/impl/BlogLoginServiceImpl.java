package top.vikingar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.LoginUser;
import top.vikingar.domain.entity.User;
import top.vikingar.domain.vo.BlogUserLoginVo;
import top.vikingar.domain.vo.UserInfoVo;
import top.vikingar.service.BlogLoginService;
import top.vikingar.utils.BeanCopyUtils;
import top.vikingar.utils.JwtUtil;
import top.vikingar.utils.RedisCache;

import java.util.Objects;

/**
 * @author vikingar
 * @time 2023/3/17 0:00
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        // provider manager 验证用户名密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名密码错误");
        }

        // 获取userid 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 用户信息存入redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);

        // user 转化为 userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        // token 和 userinfo 封装并返回
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);

        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {

        // 获取token解析获取userid , 使用SecurityContextHolder(同一个线程只能拿到自己的数据)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        // 删除redis中的用户信息
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
