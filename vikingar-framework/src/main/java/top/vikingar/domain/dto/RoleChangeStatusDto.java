package top.vikingar.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikingar
 * @time 2023/3/20 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleChangeStatusDto {

    private String roleId;

    private String status;

}
