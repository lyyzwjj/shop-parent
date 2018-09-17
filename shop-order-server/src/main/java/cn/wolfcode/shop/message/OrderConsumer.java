package cn.wolfcode.shop.message;

import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.vo.GenerateOrderVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;

/**
 * Created by WangZhe on 2018年08月28日.
 */
@Component
public class OrderConsumer {
    @Autowired
    private IOrderInfoService orderInfoService;

    @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveMsgByTopic(Message message) throws Exception {
        if (message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage) message;
            String token = mapMessage.getString("token");
            System.out.println("topic" + token);
        }
    }

    @JmsListener(destination = "queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveMsgByQueue(Message message) throws Exception {
        if (message instanceof MapMessage) {
            MapMessage mapMessage = (MapMessage) message;
            String token = mapMessage.getString("token");
            String generateOrderVoStr = mapMessage.getString("generateOrderVo");
            GenerateOrderVo generateOrderVo = JSON.parseObject(generateOrderVoStr, GenerateOrderVo.class);
            orderInfoService.generateOrder(token, generateOrderVo);
        }
    }
}
