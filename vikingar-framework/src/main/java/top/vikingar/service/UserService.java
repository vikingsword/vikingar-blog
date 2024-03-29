package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.UserDto;
import top.vikingar.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-16 23:23:54
 */
public interface UserService extends IService<User> {

    ResponseResult getUserInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(UserDto userDto);

    ResponseResult deleteUser(Long id);

    ResponseResult getUserInfoById(Long id);

    ResponseResult updateUser(UserDto userDto);
}
