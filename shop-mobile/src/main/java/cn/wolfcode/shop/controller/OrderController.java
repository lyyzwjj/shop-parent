package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.message.OrderProducer;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.vo.GenerateOrderVo;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangZhe on 2018年08月22日.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    //@Reference
    //private IOrderInfoService orderInfoService;

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public JSONResultVo generateOrder(@RequestHeader("token") String token, @RequestBody GenerateOrderVo generateOrderVo) {
        JSONResultVo result = new JSONResultVo();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("generateOrderVo", JSON.toJSONString(generateOrderVo));
            orderProducer.sendMsg(map);
            //OrderInfo orderInfo = orderInfoService.generateOrder(token, generateOrderVo);
            //result.setResult(orderInfo);
        } catch (UserException ex) {
            ex.printStackTrace();
            result.setErrorMsg(ex.getMessage());
        }
        return result;
    }
}
