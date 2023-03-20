package top.vikingar.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2023-03-16 23:23:53
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //主键
    @TableId
    private Long id;

    //用户名
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, message = "用户名长度不能小于6个字符")
    private String userName;

    //昵称
    private String nickName;
    //密码


    private String password;

    //用户类型：0代表普通用户，1代表管理员
    private String type;

    //账号状态（0正常 1停用）
    private String status;

    //邮箱

    @Email(message = "邮箱格式错误")
    private String email;

    //手机号
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phonenumber;

    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    private List<String> roleIds;





}
