package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SkuProperty extends BaseDomain {

    private Long catalogId;

    private String name;

    private Integer sort;


    public String getJsonData() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("catalogId", this.catalogId);
        map.put("name", this.name);
        map.put("sort", this.sort);
        return JSON.toJSONString(map);
    }
}