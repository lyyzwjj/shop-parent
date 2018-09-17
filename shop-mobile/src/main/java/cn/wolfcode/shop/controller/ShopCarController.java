package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.service.IShopCarService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by WangZhe on 2018年08月21日.
 */
@RestController
public class ShopCarController {
    @Reference
    private IShopCarService shopCarService;

    @PostMapping("/api/shopCar")
    public JSONResultVo addShopCar(@RequestHeader(value = "token")String token, Long productSkuId, BigDecimal number) {
        JSONResultVo result = new JSONResultVo();
        try {
            shopCarService.addShopCar(productSkuId, token, number);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
