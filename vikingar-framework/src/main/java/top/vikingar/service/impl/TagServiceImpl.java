package top.vikingar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.TagListDto;
import top.vikingar.domain.entity.Tag;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.domain.vo.TagVo;
import top.vikingar.enums.AppHttpCodeEnum;
import top.vikingar.exception.SystemException;
import top.vikingar.mapper.TagMapper;
import top.vikingar.service.TagService;
import top.vikingar.utils.BeanCopyUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 11:32:17
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        // 根据标签名或者remark查询
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        wrapper.like(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        wrapper.eq(Tag::getDelFlag, 0);

        // 封装page对象
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, wrapper);

        // 返回pageVo
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTagList(TagListDto tagListDto) {
        if (!StringUtils.hasText(tagListDto.getName())) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        } else if (!StringUtils.hasText(tagListDto.getRemark())) {
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NOT_NULL);
        }
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTagById(Long id) {
        // 在配置文件中配置了mybatis删除字段后，删除即为更新
        // https://blog.csdn.net/qq_22701869/article/details/112158255
        tagMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTagById(Long id) {
        Tag tag = getById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }

}
