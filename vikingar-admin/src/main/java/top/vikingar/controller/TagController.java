package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.dto.TagListDto;
import top.vikingar.domain.entity.Tag;
import top.vikingar.domain.vo.PageVo;
import top.vikingar.service.TagService;

/**
 * @author vikingar
 * @time 2023/3/18 12:39
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.getTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping()
    public ResponseResult add(@RequestBody TagListDto tagListDto) {
        return tagService.addTagList(tagListDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return tagService.deleteTagById(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable("id") Long id) {
        return tagService.getTagById(id);
    }

    @PutMapping()
    public ResponseResult update(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }
}
