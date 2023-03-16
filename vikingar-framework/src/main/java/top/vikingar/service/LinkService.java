package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-03-16 23:01:43
 */
public interface LinkService extends IService<Link> {

    /**
     * getAllLink
     * @return ResponseResult
     */
    ResponseResult getAllLink();

}
