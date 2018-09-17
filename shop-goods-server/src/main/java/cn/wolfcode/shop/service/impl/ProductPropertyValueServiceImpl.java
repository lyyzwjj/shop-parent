package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductPropertyValue;
import cn.wolfcode.shop.mapper.ProductPropertyValueMapper;
import cn.wolfcode.shop.service.IProductPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月21日.
 */
@Service
public class ProductPropertyValueServiceImpl implements IProductPropertyValueService {
    @Autowired
    private ProductPropertyValueMapper productPropertyValueMapper;

    @Override
    public List<ProductPropertyValue> selectListByProductId(Long productId) {
        return productPropertyValueMapper.selectListByProductId(productId);
    }
}
