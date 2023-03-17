package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-16 23:23:54
 */
public interface UserService extends IService<User> {

    ResponseResult getUserInfo();

}
