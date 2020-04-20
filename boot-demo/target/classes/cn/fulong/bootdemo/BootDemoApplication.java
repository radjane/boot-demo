package cn.fulong.bootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:58 上午
 * @desc 开启定时任务
 */
@EnableScheduling
@MapperScan(basePackages = {"cn.fulong.bootdemo.dao"})
@SpringBootApplication
public class BootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class, args);
    }

}
