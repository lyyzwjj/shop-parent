package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.qo.QueryObject;
import cn.wolfcode.shop.vo.ProductVo;

import java.util.List;


public interface IProductService {

    List<Product> getAllProduct();

    PageResult productPage(QueryObject qo);

    /**
     * 保存商品
     */
    void save(ProductVo productVo);

    /**
     * 通过商品id获取商品对象
     * @param productId
     * @return
     */
    Product getProductById(Long productId);

    /**
     * 根据分类id查询商品的个数
     * @param catalogId
     * @return
     */
    Integer queryCountByCatalogId(Long catalogId);
}
