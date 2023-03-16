package top.vikingar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vikingar.domain.ResponseResult;
import top.vikingar.service.LinkService;

/**
 * @author vikingar
 * @time 2023/3/16 22:44
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
