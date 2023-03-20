package top.vikingar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/20 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddDto {

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    private List<String> menuIds;

    private String remark;
}
