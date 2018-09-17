package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductPropertyValue;

import java.util.List;

/**
 * Created by WangZhe on 2018/8/21.
 */
public interface IProductPropertyValueService {
    List<ProductPropertyValue> selectListByProductId(Long productId);
}
