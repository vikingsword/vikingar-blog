package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "友链", description = "友链相关接口")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("getAllLink")
    @ApiOperation(value = "友链列表", notes = "获取所有友链")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
