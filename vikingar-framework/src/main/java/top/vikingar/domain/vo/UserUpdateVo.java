package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.vikingar.domain.entity.Role;
import top.vikingar.domain.entity.User;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/20 18:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateVo {

    private List<String> roleIds;

    private List<Role> roles;

    private UserInfoUpdateVo user;

}
