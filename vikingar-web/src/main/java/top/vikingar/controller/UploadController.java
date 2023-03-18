package top.vikingar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.vikingar.domain.ResponseResult;
import top.vikingar.service.UploadService;

/**
 * @author vikingar
 * @time 2023/3/17 15:43
 */
@RestController
@Api(tags = "文件上传", description = "文件上传相关接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public ResponseResult upload(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
