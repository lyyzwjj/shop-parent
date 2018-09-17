package cn.wolfcode.shop.message;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.Map;

/**
 * Created by WangZhe on 2018年08月28日.
 */
@Component
public class OrderProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    private Destination destination = new ActiveMQQueue("queue");
    private Destination destination1 = new ActiveMQTopic("topic");

    public void sendMsg(Map map) {
        jmsTemplate.convertAndSend(destination, map);
        //jmsTemplate.convertAndSend(destination1, map);
    }
}
