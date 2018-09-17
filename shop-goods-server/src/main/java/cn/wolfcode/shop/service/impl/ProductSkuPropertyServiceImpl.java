package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductSkuProperty;
import cn.wolfcode.shop.mapper.ProductSkuPropertyMapper;
import cn.wolfcode.shop.service.IProductSkuPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月22日.
 */
@Service
public class ProductSkuPropertyServiceImpl implements IProductSkuPropertyService {
    @Autowired
    private ProductSkuPropertyMapper productSkuPropertyMapper;
    @Override
    public List<ProductSkuProperty> selectProductSkuPropertyListByProductSkuId(Long productSkuId) {
        return productSkuPropertyMapper.selectByProductSkuId(productSkuId);
    }
}
