package top.vikingar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vikingar.domain.entity.User;
import top.vikingar.mapper.UserMapper;
import top.vikingar.service.UserService;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-16 23:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
