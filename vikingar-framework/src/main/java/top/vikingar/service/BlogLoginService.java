package top.vikingar.service;

import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.User;

/**
 * @author vikingar
 * @time 2023/3/16 23:57
 */
public interface BlogLoginService {

    /**
     * login
     * @param user user
     * @return response
     */
    ResponseResult login(User user);
}
