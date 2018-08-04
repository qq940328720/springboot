package cold.face.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration//这是一个配置Spring的配置类
@SpringBootApplication(scanBasePackages = "cold.face")//@SpringBootApplication：Spring Boot项目的核心注解，主要目的是开启自动配置。
@MapperScan(basePackages = "cold.face.dal.dao")
public class NoodlesApplication {

    public static void main(String[] args) {
        //在SprintApplication.run之前调用,可以完全关闭重启支持,也可以放在配置文件
//        System.setProperty("spring.devtools.restart.enabled", "false");

//        SpringApplication.run(NoodlesApplication.class, args);

        //启动spring boot应用
        SpringApplication sa = new SpringApplication(NoodlesApplication.class);
        //禁用devTools热部署
//        System.setProperty("spring.devtools.restart.enabled", "false");
        //禁用命令行更改application.properties属性
        sa.setAddCommandLineProperties(false);
        sa.run(args);
    }

}
