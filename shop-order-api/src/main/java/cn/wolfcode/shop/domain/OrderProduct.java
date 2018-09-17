package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter@Getter
public class OrderProduct extends BaseDomain {
    private Long orderId;
    private Long skuId;
    private String productName;
    private BigDecimal productNumber;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;
    private String productImg;
    private String skuType;
}