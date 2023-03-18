package top.vikingar.service;

import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
