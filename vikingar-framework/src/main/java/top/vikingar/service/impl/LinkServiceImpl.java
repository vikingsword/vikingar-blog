package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vikingar.constants.SystemConstants;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.entity.Link;
import top.vikingar.domain.vo.LinkInfoVo;
import top.vikingar.domain.vo.LinkVo;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.mapper.LinkMapper;
import top.vikingar.service.LinkService;
import top.vikingar.utils.BeanCopyUtils;

import java.util.List;
import java.util.Objects;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-03-16 23:01:43
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    private LinkService linkService;

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(wrapper);
        // 封装Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult getLink(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(name), Link::getName, name);
        wrapper.eq(Objects.nonNull(status),Link::getStatus,status);
        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page,wrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(page.getRecords(), LinkVo.class);
        PageVo pageVo = new PageVo(linkVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult add(LinkVo linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        linkService.save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLinkInfo(Long id) {
        Link link = getById(id);
        LinkInfoVo linkInfoVo = BeanCopyUtils.copyBean(link, LinkInfoVo.class);
        return ResponseResult.okResult(linkInfoVo);
    }

    @Override
    public ResponseResult updateLink(LinkInfoVo linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(Long id) {
        linkService.removeById(id);
        return ResponseResult.okResult();
    }


}
