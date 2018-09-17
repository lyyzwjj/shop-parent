package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.ProductSkuMapper;
import cn.wolfcode.shop.mapper.ProductSkuPropertyMapper;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月19日.
 */
@Service
public class ProductSkuServiceImpl implements IProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuPropertyMapper productSkuPropertyMapper;

    @Override
    public List<ProductSku> selectProductSkusByProductId(Long productId) {
        return productSkuMapper.selectProductSkusByProductId(productId);
    }

    @Override
    public void save(GenerateSkuVo generateSkuVo) {
        List<ProductSku> productSkuList = generateSkuVo.getProductSkuList();
        for (ProductSku productSku : productSkuList) {
            productSku.setProductId(generateSkuVo.getProduct().getId());
            productSkuMapper.insert(productSku);
            List<ProductSkuProperty> productSkuPropertyList = productSku.getProductSkuPropertyList();
            for (ProductSkuProperty productSkuProperty : productSkuPropertyList) {
                productSkuProperty.setProductSkuId(productSku.getId());
                productSkuPropertyMapper.insert(productSkuProperty);
            }
        }
    }

    @Override
    public ProductSku getProductSku(Long productSkuId) {
        return productSkuMapper.selectByPrimaryKey(productSkuId);
    }
}
