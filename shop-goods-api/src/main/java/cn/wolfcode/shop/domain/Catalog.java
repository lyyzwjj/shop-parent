package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Catalog extends BaseDomain {
    private String name;//分类名称
    private String code;//分类编号
    private Integer sort;//分类排序
    private Long pId;//父分类id
    private boolean isParent;//是否父节点
    private Integer propertyCount;//属性个数
    private Integer productCount;//商品个数
    private String catalogImage;//分类图片
    public String getJsonData() {
        Map<String, Object> map = new HashMap();
        map.put("id", getId());
        map.put("name", name);
        map.put("sort", sort);
        map.put("isParent", isParent);
        map.put("code", code);
        return JSON.toJSONString(map);
    }
}