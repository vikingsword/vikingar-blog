package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikingar
 * @time 2023/3/20 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    private String status;

    private String sex;

    private String email;

    private String userName;

    private String phonenumber;


}
