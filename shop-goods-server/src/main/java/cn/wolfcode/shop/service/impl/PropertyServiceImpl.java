package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.mapper.PropertyMapper;
import cn.wolfcode.shop.mapper.PropertyValueMapper;
import cn.wolfcode.shop.service.IPropertyService;
import cn.wolfcode.shop.service.IPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月17日.
 */
@Transactional
@Service
public class PropertyServiceImpl implements IPropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private IPropertyValueService propertyValueService;

    @Override
    public List<Property> selectPropertyByCatalogId(Long catalogId) {
        List<Property> properties = propertyMapper.selectProperyByCatalogId(catalogId);
        for (Property property : properties) {
            if (property.getType() == Property.TYPE_SELECT) {
                property.setPropertyValueList(propertyValueService.selectProperyValuesByPropertyId(property.getId()));
            }
        }
        return properties;
    }


    @Override
    public void saveOrUpdate(Property property) {
        if (property.getId() == null) {
            propertyMapper.insert(property);
        } else {
            propertyMapper.updateByPrimaryKey(property);
        }
    }

    @Override
    public void delete(Long propertyId) {
        propertyMapper.deleteByPrimaryKey(propertyId);
    }


}
