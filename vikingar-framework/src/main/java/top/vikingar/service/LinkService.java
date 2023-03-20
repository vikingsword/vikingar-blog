package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Link;
import top.vikingar.domain.vo.LinkInfoVo;
import top.vikingar.domain.vo.LinkVo;


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

    ResponseResult getLink(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult add(LinkVo linkDto);

    ResponseResult getLinkInfo(Long id);

    ResponseResult updateLink(LinkInfoVo linkDto);

    ResponseResult deleteLink(Long id);
}
