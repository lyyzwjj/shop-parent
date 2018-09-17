package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.mapper.SkuPropertyMapper;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhe on 2018年08月17日.
 */
@Transactional
@Service
public class SkuPropertyServiceImpl implements ISkuPropertyService {
    @Autowired
    private SkuPropertyMapper skuPropertyMapper;

    @Override
    public List<SkuProperty> selectSkuPropertyByCatalogId(Long catalogId) {
        return skuPropertyMapper.selectProperyByCatalogId(catalogId);
    }


    @Override
    public void saveOrUpdate(SkuProperty skuProperty) {
        if (skuProperty.getId() == null) {
            skuPropertyMapper.insert(skuProperty);
        } else {
            skuPropertyMapper.updateByPrimaryKey(skuProperty);
        }
    }

    @Override
    public void delete(Long skuPropertyId) {
        skuPropertyMapper.deleteByPrimaryKey(skuPropertyId);
    }

    @Override
    public List<SkuProperty> selectSkuPropertiesByProductId(Long productId) {
        return skuPropertyMapper.selectSkuPropertiesByProductId(productId);
    }

    @Override
    public SkuProperty getBySkuPropertyId(Long skuPropertyId) {
        return skuPropertyMapper.selectByPrimaryKey(skuPropertyId);
    }
    @Override
    public List<Map<String, Object>> generateSku(GenerateSkuVo generateSkuVo) {
        List<SkuPropertyValue> skuPropertyValueList = generateSkuVo.getSkuPropertyValueList();
        Map<Long,List<SkuPropertyValue>> map = new HashMap<>();
        List<SkuPropertyValue> skuPropertyValues;
        for(SkuPropertyValue spv:skuPropertyValueList){
            skuPropertyValues = map.get(spv.getSkuPropertyId());
            if(skuPropertyValues==null){
                skuPropertyValues = new ArrayList<>();
                map.put(spv.getSkuPropertyId(),skuPropertyValues);
            }
            skuPropertyValues.add(spv);
        }

        //===============================================================
        List<List<SkuPropertyValue>> sourceData = new ArrayList(map.values());
        List<List<SkuPropertyValue>> targetData = new ArrayList<>();
        recursionGenerateSku(sourceData,targetData,0,new ArrayList<>());
        //===============================================================
        List<Map<String,Object>> resultData = new ArrayList<>();
        Map<String,Object> record;
        String skuCodePrefix = skuPropertyMapper.getCodePrefix(generateSkuVo.getProduct().getCatalog().getId())+generateSkuVo.getProduct().getId();
        List<SkuPropertyValue> spvList;
        for(int i=0;i<targetData.size();i++){
            spvList = targetData.get(i);
            record = new HashMap<>();
            record.put("price",generateSkuVo.getProduct().getBasePrice());
            record.put("code",skuCodePrefix+((i+1)>9?(i+1):"0"+(i+1)));
            for(SkuPropertyValue spv:spvList){
                record.put(spv.getSkuPropertyId()+"",spv.getValue());
            }
            resultData.add(record);
        }
        return resultData;
    }
    /**
     *
     * @param sourceData 源数据
     * @param targetData 目标数据集合
     * @param level      递归的层数
     * @param appendData 需要往下一层传递的参数
     */
    public void recursionGenerateSku(List<List<SkuPropertyValue>> sourceData,
                                     List<List<SkuPropertyValue>> targetData,
                                     int level,
                                     List<SkuPropertyValue> appendData){
        //判断是否最后一层的数据
        if(level<sourceData.size()-1){
            //不是最后一层
            List<SkuPropertyValue> innerSkuPropertyValueList = sourceData.get(level);
            for(SkuPropertyValue spv:innerSkuPropertyValueList){
                List<SkuPropertyValue> innerAppendData = new ArrayList<>(appendData);
                innerAppendData.add(spv);
                recursionGenerateSku(sourceData,targetData,level+1,innerAppendData);
            }
        }else{
            //是最后一层
            List<SkuPropertyValue> innerSkuPropertyValueList = sourceData.get(level);
            for(SkuPropertyValue spv:innerSkuPropertyValueList){
                List<SkuPropertyValue> innerAppendData = new ArrayList<>(appendData);
                innerAppendData.add(spv);
                targetData.add(innerAppendData);
            }
        }
    }


}
