package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/20 11:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RoleMenuTreeVo {

    private List<MenuTreeVo> menus;

    private List<String> checkedKeys;

}
