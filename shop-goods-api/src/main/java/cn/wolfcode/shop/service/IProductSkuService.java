package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.vo.GenerateSkuVo;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月19日.
 */

public interface IProductSkuService {
    /**
     * 根据产品Id找到产品Sku集合
     *
     * @param productId
     * @return
     */
    List<ProductSku> selectProductSkusByProductId(Long productId);

    /**
     * 保存生成的skuVo
     *
     * @param generateSkuVo
     */
    void save(GenerateSkuVo generateSkuVo);

    /**
     * 根据productSkuId获取productSku
     *
     * @param productSkuId
     * @return
     */
    ProductSku getProductSku(Long productSkuId);
}
