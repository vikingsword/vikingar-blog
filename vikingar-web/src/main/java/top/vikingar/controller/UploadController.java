package top.vikingar.controller;

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
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
