package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.mapper.CatalogMapper;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.service.IPropertyService;
import cn.wolfcode.shop.util.RedisConstants;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by WangZhe on 2018年08月15日.
 */
@Service
@Transactional
public class CatalogServiceImpl implements ICatalogService {
    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IProductService productService;
    @Autowired
    private IPropertyService propertyService;

    @Override
    public List<Catalog> selectAll() {
        List<Catalog> catalogs = catalogMapper.selectAll();
        return catalogMapper.selectAll();
    }


    @Override
    public void updateSort(List<Long> ids) {
        Catalog catalog;
        for (int i = 0; i < ids.size(); i++) {
            catalog = catalogMapper.selectByPrimaryKey(ids.get(i));
            catalog.setSort(i + 1);
            catalogMapper.updateByPrimaryKey(catalog);
        }
        this.refreshCache();
    }


    @Override
    public void saveOrUpdate(Catalog catalog) {
        if (catalog.getId() != null) {
            catalogMapper.updateByPrimaryKey(catalog);
        } else {
            Catalog parent = catalogMapper.queryParentByParentId(catalog.getPId());
            if (!parent.isParent()) {
                parent.setParent(true);
                catalogMapper.updateByPrimaryKey(parent);
            }
            catalogMapper.insert(catalog);
        }
        this.refreshCache();
    }

    @Override
    public void delete(Long id) {
        int count = catalogMapper.queryChildCountByChildId(id);
        if (count == 1) {
            catalogMapper.updateParentStateByChildId(id);
        }
        catalogMapper.deleteByPrimaryKey(id);
        this.refreshCache();
    }

    @Override
    public String selectAllFromCache() {
        String catalogAllJSONString = (String) redisTemplate.opsForValue().get(RedisConstants.CATALOG_ALL);
        if (catalogAllJSONString == null) {
            catalogAllJSONString = JSON.toJSONString(catalogMapper.selectAll());
            redisTemplate.opsForValue().set(RedisConstants.CATALOG_ALL, catalogAllJSONString);
        }
        return catalogAllJSONString;
    }

    @Override
    public String refreshCache() {
        String catalogAllJSONString = JSON.toJSONString(catalogMapper.selectAll());
        redisTemplate.opsForValue().set(RedisConstants.CATALOG_ALL, catalogAllJSONString);
        return catalogAllJSONString;
    }
    @Override
    public List<Catalog> queryChildrenByParentId(Long catalogId) {
        List<Catalog> catalogs = catalogMapper.queryChildrenByParentId(catalogId);
        String propertyCountKey;
        String productCountKey;
        for(Catalog catalog:catalogs){
            propertyCountKey = MessageFormat.format(RedisConstants.CATALOG_PROPERTY_COUNT,catalog.getId());
            Integer propertyCount = (Integer) redisTemplate.opsForValue().get(propertyCountKey);
            if(propertyCount==null){
                //propertyCount = propertyService.queryCountByCatalogId(catalog.getId());
                redisTemplate.opsForValue().set(propertyCountKey,propertyCount);
            }
            catalog.setPropertyCount(propertyCount);
            productCountKey = MessageFormat.format(RedisConstants.CATALOG_PRODUCT_COUNT,catalog.getId());
            Integer productCount = (Integer) redisTemplate.opsForValue().get(productCountKey);
            if(productCount==null){
                productCount = productService.queryCountByCatalogId(catalog.getId());
                redisTemplate.opsForValue().set(productCountKey,productCount);
            }
            catalog.setProductCount(productCount);
        }
        return catalogs;
    }

}
