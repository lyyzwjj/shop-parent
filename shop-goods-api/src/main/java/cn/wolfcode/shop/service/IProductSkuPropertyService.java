package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductSkuProperty;

import java.util.List;

/**
 * Created by WangZhe on 2018/8/19.
 */
public interface IProductSkuPropertyService {
    List<ProductSkuProperty> selectProductSkuPropertyListByProductSkuId(Long productSkuId);

}
