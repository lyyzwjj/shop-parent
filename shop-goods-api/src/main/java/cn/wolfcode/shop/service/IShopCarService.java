package cn.wolfcode.shop.service;


import java.math.BigDecimal;

/**
 * Created by WangZhe on 2018/8/21.
 */
public interface IShopCarService {
    void addShopCar(Long productSkuId,String token, BigDecimal number);
}
