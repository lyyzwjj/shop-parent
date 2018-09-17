package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.domain.SkuPropertyValue;

import java.util.List;

/**
 * Created by WangZhe on 2018/8/19.
 */
public interface ISkuPropertyValueService {
    void saveOrUpdateSkuPropertyValues(List<SkuPropertyValue> skuPropertyValues);

    void deleteSkuPropertyValue(Long skuPropertyValueId);

    List<SkuPropertyValue> selectSkuProperyValuesBySkuPropertyId(Long skuPropertyId);

}
