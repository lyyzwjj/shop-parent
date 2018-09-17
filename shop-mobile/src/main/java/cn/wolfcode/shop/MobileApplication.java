package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by WangZhe on 2018年08月20日.
 */
@SpringBootApplication
@PropertySource({"classpath:redis.properties", "classpath:zookeeper.properties", "classpath:activemq.properties"})
public class    MobileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }
}
