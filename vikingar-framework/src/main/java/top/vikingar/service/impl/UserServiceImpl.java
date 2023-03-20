package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.UserDto;
import top.vikingar.domain.entity.Role;
import top.vikingar.domain.entity.User;
import top.vikingar.domain.entity.UserRole;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.domain.vo.UserInfoVo;
import top.vikingar.domain.vo.UserInfoUpdateVo;
import top.vikingar.domain.vo.UserUpdateVo;
import top.vikingar.enums.AppHttpCodeEnum;
import top.vikingar.exception.SystemException;
import top.vikingar.mapper.UserMapper;
import top.vikingar.service.RoleService;
import top.vikingar.service.UserRoleService;
import top.vikingar.service.UserService;
import top.vikingar.utils.BeanCopyUtils;
import top.vikingar.utils.SecurityUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-16 23:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

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

    @Override
    public ResponseResult register(User user) {

        // 用户校验 非空判断 todo 使用validation修改
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        } else if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }

        // 数据存在性判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        // 密码加密 存储数据库
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        save(user);

        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(userName),User::getUserName,userName);
        wrapper.eq(Objects.nonNull(phonenumber),User::getPhonenumber,phonenumber);
        wrapper.eq(Objects.nonNull(status),User::getStatus,status);

        Page<User> page = new Page<>(pageNum, pageSize);
        page(page,wrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addUser(UserDto userDto) {
        String encode = passwordEncoder.encode(userDto.getPassword());
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        user.setPassword(encode);
        userService.save(user);
        userDto.getRoleIds().stream().map(r -> {
            UserRole userRole = new UserRole(user.getId(), Long.valueOf(r));
            return userRoleService.save(userRole);
        }).collect(Collectors.toList());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUser(Long id) {
        userMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserInfoById(Long id) {
        User user = getById(id);
        UserInfoUpdateVo userInfoUpdateVo = BeanCopyUtils.copyBean(user, UserInfoUpdateVo.class);
        List<Role> roles = roleService.list();
        List<String> roleIds = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, id))
                .stream().map(ur -> String.valueOf(ur.getRoleId())).collect(Collectors.toList());
        UserUpdateVo userUpdateVo = new UserUpdateVo(roleIds, roles, userInfoUpdateVo);
        return ResponseResult.okResult(userUpdateVo);
    }

    @Override
    @Transactional
    public ResponseResult updateUser(UserDto userDto) {
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        userService.updateById(user);
        // 先删除在添加
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,userDto.getId()));
        userDto.getRoleIds().stream().map(r -> {
            UserRole userRole = new UserRole(userDto.getId(), Long.valueOf(r));
            return userRoleService.save(userRole);
        }).collect(Collectors.toList());

        return ResponseResult.okResult();
    }


    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickName,nickName);
        return count(wrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        return count(wrapper) > 0;
    }


}
