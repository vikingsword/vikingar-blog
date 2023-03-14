package top.vikingar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vikingar
 * @time 2023/3/11 0:42
 */
@SpringBootApplication
@MapperScan("top.vikingar.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}