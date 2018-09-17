package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.PropertyValue;

import java.util.List;

/**
 * Created by WangZhe on 2018/8/19.
 */
public interface IPropertyValueService {
    void saveOrUpdatePropertyValues(List<PropertyValue> propertyValues);

    void deletePropertyValue(Long propertyValueId);

    List<PropertyValue> selectProperyValuesByPropertyId(Long propertyId);

}
