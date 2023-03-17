package top.vikingar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;
import top.vikingar.domain.vo.UserInfoVo;
import top.vikingar.mapper.UserMapper;
import top.vikingar.service.UserService;
import top.vikingar.utils.BeanCopyUtils;
import top.vikingar.utils.SecurityUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-16 23:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public ResponseResult getUserInfo() {

        // 获取用户id
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);

        // 根据id查询信息，封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }


}
