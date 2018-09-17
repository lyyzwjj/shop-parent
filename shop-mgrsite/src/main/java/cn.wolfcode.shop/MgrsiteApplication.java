package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by WangZhe on 2018年08月15日.
 */
@SpringBootApplication
@PropertySource({"classpath:zookeeper.properties"})
public class MgrsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(MgrsiteApplication.class,args);
    }
}
