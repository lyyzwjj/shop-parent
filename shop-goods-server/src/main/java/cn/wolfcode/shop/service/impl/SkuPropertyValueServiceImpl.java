package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.mapper.SkuPropertyValueMapper;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月19日.
 */
@Service
public class SkuPropertyValueServiceImpl implements ISkuPropertyValueService {
    @Autowired
    private SkuPropertyValueMapper skuPropertyValueMapper;

    @Override
    public void saveOrUpdateSkuPropertyValues(List<SkuPropertyValue> skuPropertyValues) {
        for (SkuPropertyValue skuPropertyValue : skuPropertyValues) {
            if (skuPropertyValue.getId() == null) {
                skuPropertyValueMapper.insert(skuPropertyValue);
            } else {
                skuPropertyValueMapper.updateByPrimaryKey(skuPropertyValue);
            }
        }
    }

    @Override
    public void deleteSkuPropertyValue(Long skuPropertyId) {
        skuPropertyValueMapper.deleteByPrimaryKey(skuPropertyId);
    }

    @Override
    public List<SkuPropertyValue> selectSkuProperyValuesBySkuPropertyId(Long skuPropertyId) {
        return skuPropertyValueMapper.selectSkuProperyValuesBySkuPropertyId(skuPropertyId);
    }

}
