package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.mapper.PropertyValueMapper;
import cn.wolfcode.shop.service.IPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月19日.
 */
@Service
public class PropertyValueServiceImpl implements IPropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Override
    public void saveOrUpdatePropertyValues(List<PropertyValue> propertyValues) {
        for (PropertyValue propertyValue : propertyValues) {
            if (propertyValue.getId() == null) {
                propertyValueMapper.insert(propertyValue);
            } else {
                propertyValueMapper.updateByPrimaryKey(propertyValue);
            }
        }
    }

    @Override
    public void deletePropertyValue(Long propertyId) {
        propertyValueMapper.deleteByPrimaryKey(propertyId);
    }

    @Override
    public List<PropertyValue> selectProperyValuesByPropertyId(Long propertyId) {
        return propertyValueMapper.selectProperyValuesByPropertyId(propertyId);
    }
}
