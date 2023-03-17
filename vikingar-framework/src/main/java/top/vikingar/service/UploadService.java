package top.vikingar.service;

import org.springframework.web.multipart.MultipartFile;
import top.vikingar.domain.ResponseResult;

/**
 * @author vikingar
 * @time 2023/3/17 15:45
 */
public interface UploadService {


    ResponseResult uploadImg(MultipartFile img);
}
