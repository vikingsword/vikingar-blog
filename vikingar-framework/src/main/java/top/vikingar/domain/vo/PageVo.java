package top.vikingar.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vikingar
 * @time 2023/3/16 21:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    private List rows;

    private Long total;

}
