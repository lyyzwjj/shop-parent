package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.service.IPropertyValueService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by WangZhe on 2018年08月19日.
 */
@Controller
public class PropertyValueController {
    @Reference
    private IPropertyValueService propertyService;

    @RequestMapping("/propertyValue/get/{propertyId}")
    public String propertyValue(Model model, @PathVariable Long propertyId) {
        List<PropertyValue> propertieValues = propertyService.selectProperyValuesByPropertyId(propertyId);
        model.addAttribute("propertyValueList", propertieValues);
        return "/property/property_value_list";
    }

    @RequestMapping("/propertyValue/save")
    @ResponseBody
    public JSONResultVo savePropertyValue(@RequestBody List<PropertyValue> propertyValues) {
        JSONResultVo result = new JSONResultVo();
        try {
            propertyService.saveOrUpdatePropertyValues(propertyValues);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/propertyValue/delete/{propertyId}")
    @ResponseBody
    public JSONResultVo deletePropertyValue(@PathVariable Long propertyId) {
        JSONResultVo result = new JSONResultVo();
        try {
            propertyService.deletePropertyValue(propertyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
