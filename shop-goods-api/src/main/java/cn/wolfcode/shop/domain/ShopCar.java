package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by WangZhe on 2018年08月21日.
 */

@Setter
@Getter
public class ShopCar implements Serializable {
    private String productName;
    private String productImage;
    private Long productSkuId;
    private BigDecimal productPrice;
    private BigDecimal number;
    private String productSkuPropertyStr;

}
