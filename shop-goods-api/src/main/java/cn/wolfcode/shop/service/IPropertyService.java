package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.domain.PropertyValue;

import java.util.List;

/**
 * Created by WangZhe on 2018/8/17.
 */
public interface IPropertyService {
    List<Property> selectPropertyByCatalogId(Long catalogId);

    void saveOrUpdate(Property property);

    void delete(Long propertyId);


}
