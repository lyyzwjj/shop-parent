package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.vo.GenerateOrderVo;

/**
 * Created by wolfcode on 2018/8/20.
 */
public interface IOrderInfoService {
    /**
     * 生成订单服务
     * @param token
     * @param generateOrderVo
     */
    OrderInfo generateOrder(String token, GenerateOrderVo generateOrderVo);
}
