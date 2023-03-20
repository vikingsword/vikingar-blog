package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vikingar.domain.ResponseResult;
import top.vikingar.domain.vo.LinkInfoVo;
import top.vikingar.domain.vo.LinkVo;
import top.vikingar.service.LinkService;

/**
 * @author vikingar
 * @time 2023/3/20 19:24
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.getLink(pageNum, pageSize, name, status);
    }

    @PostMapping
    public ResponseResult add(@RequestBody LinkVo linkDto) {
        return linkService.add(linkDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable("id") Long id) {
        return linkService.getLinkInfo(id);
    }

    @PutMapping
    public ResponseResult update(@RequestBody LinkInfoVo linkDto) {
        return linkService.updateLink(linkDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return linkService.deleteLink(id);
    }
}
