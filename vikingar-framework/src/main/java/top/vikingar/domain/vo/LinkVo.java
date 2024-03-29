package top.vikingar.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vikingar
 * @time 2023/3/16 23:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    private Long id;

    private String name;

    private String logo;

    private String description;

    //网站地址
    private String address;

}
