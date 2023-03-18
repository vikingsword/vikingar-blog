package top.vikingar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.TagListDto;
import top.vikingar.domain.entity.Tag;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 11:32:17
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTagList(TagListDto tagListDto);

    ResponseResult deleteTagById(Long id);

    ResponseResult getTagById(Long id);

    ResponseResult updateTag(Tag tag);

    List<TagVo> listAllTag();

}
