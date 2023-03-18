package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikingar
 * @time 2023/3/15 5:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPageVo {

    private Long id;

    private String name;

    //描述
    private String description;


    private String status;

}
