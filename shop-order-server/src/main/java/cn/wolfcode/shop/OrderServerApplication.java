package cn.wolfcode.shop;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * Created by WangZhe on 2018年08月20日.
 */

@SpringBootApplication
@MapperScan("cn.wolfcode.shop.mapper")
@PropertySource({"classpath:redis.properties", "classpath:zookeeper.properties", "classpath:activemq.properties"})
public class OrderServerApplication {

    /**
     * 用于监听Topic模型消息的容器bean
     *
     * @param jmsConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //是否使用订阅和发布的模型监听器，默认为false，也就是说默认使用的是点对点的模型监听器
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(jmsConnectionFactory);
        return bean;
    }

    /**
     * 用于监听Queue模型消息的容器bean
     *
     * @param jmsConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(jmsConnectionFactory);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }
}
