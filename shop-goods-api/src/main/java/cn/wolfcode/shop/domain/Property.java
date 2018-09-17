package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Property extends BaseDomain {
    public static final Integer TYPE_INPUT = 0;
    public static final Integer TYPE_SELECT = 1;

    private Long catalogId;

    private String name;

    private Integer sort;

    private Integer type;

    private List<PropertyValue> propertyValueList;

    public String getJsonData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("catalogId", this.catalogId);
        map.put("name", this.name);
        map.put("sort", this.sort);
        map.put("type", this.type);
        map.put("propertyValueList", this.propertyValueList);
        return JSON.toJSONString(map);
    }
}