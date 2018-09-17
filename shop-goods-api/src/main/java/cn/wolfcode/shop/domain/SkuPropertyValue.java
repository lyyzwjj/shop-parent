package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkuPropertyValue extends BaseDomain {
    private Long skuPropertyId;
    private String value;
}