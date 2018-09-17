package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Catalog;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月15日.
 */

public interface ICatalogService {
    /**
     * 获取所有的商品列表
     *
     * @return
     */
    List<Catalog> selectAll();

    /**
     * 根据父Catalog的id找出所有的子Catalog
     *
     * @param catalogId
     * @return
     */
    List<Catalog> queryChildrenByParentId(Long catalogId);

    /**
     * 修改商品的排序
     *
     * @param ids
     */
    void updateSort(List<Long> ids);


    /**
     * 保存或者更新商品目录
     *
     * @param catalog
     */
    void saveOrUpdate(Catalog catalog);

    /**
     * 根据id删除商品目录
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 从redis缓存中或区间所有的catalog集合
     *
     * @return
     */
    String selectAllFromCache();

    /**
     * 刷新redis缓存
     *
     * @return
     */
    String refreshCache();

}
