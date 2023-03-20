package top.vikingar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikingar
 * @time 2023/3/20 19:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAddDto {

    private String name;

    //描述
    private String description;

    //状态0:正常,1禁用
    private String status;


}
