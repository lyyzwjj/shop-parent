package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.util.RedisConstants;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by WangZhe on 2018年08月21日.
 */
@Service
public class ShopCarServiceImpl implements IShopCarService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductSkuService productSkuService;
    @Autowired
    private IProductSkuPropertyService productSkuPropertyService;

    @Override
    public void addShopCar(Long productSkuId, String token, BigDecimal number) {
        String userKey = MessageFormat.format(RedisConstants.USER_LOGIN_TOKEN, token);
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(userKey);
        if (userLogin == null) {
            throw new UserException("非法操作");
        }
        String shopCarKey = MessageFormat.format(RedisConstants.USER_SHOP_CAR, userLogin.getId(), productSkuId);
        ShopCar shopCar = (ShopCar) redisTemplate.opsForValue().get(shopCarKey);
        ProductSku productSku = productSkuService.getProductSku(productSkuId);
        Product product = productService.getProductById(productSku.getProductId());
        if (shopCar == null) {
            shopCar = new ShopCar();
            shopCar.setNumber(number);
            shopCar.setProductImage(product.getImage());
            shopCar.setProductName(product.getName());
            shopCar.setProductPrice(productSku.getPrice());
            shopCar.setProductSkuId(productSkuId);
            List<ProductSkuProperty> productSkuProperties = productSkuPropertyService.selectProductSkuPropertyListByProductSkuId(productSkuId);
            StringBuilder productSkuPropertyStr = new StringBuilder(100);
            for (ProductSkuProperty productSkuProperty : productSkuProperties) {
                productSkuPropertyStr.append(productSkuProperty.getSkuProperty().getName()).append(":")
                        .append(productSkuProperty.getValue()).append(" ");
            }
            shopCar.setProductSkuPropertyStr(productSkuPropertyStr.toString());
            redisTemplate.opsForValue().set(shopCarKey, shopCar);
        } else {
            shopCar.setNumber(shopCar.getNumber().add(number));
            redisTemplate.opsForValue().set(shopCarKey, shopCar);
        }
    }
}
