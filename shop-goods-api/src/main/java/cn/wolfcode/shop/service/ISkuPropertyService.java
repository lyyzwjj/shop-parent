package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.vo.GenerateSkuVo;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhe on 2018/8/17.
 */
public interface ISkuPropertyService {
    List<SkuProperty> selectSkuPropertyByCatalogId(Long catalogId);

    void saveOrUpdate(SkuProperty skuProperty);

    void delete(Long skuPropertyId);


    List<SkuProperty> selectSkuPropertiesByProductId(Long productId);

    SkuProperty getBySkuPropertyId(Long skuPropertyId);

    List<Map<String,Object>> generateSku(GenerateSkuVo generateSkuVo);
}
