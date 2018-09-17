package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
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
public class SkuPropertyValueController {
    @Reference
    private ISkuPropertyValueService skuPropertyService;

    @RequestMapping("/skuPropertyValue/get/{skuPropertyId}")
    public String skuPropertyValue(Model model, @PathVariable Long skuPropertyId) {
        List<SkuPropertyValue> propertieValues = skuPropertyService.selectSkuProperyValuesBySkuPropertyId(skuPropertyId);
        model.addAttribute("skuPropertyValueList", propertieValues);
        return "/skuProperty/skuProperty_value_list";
    }

    @RequestMapping("/skuPropertyValue/save")
    @ResponseBody
    public JSONResultVo saveSkuPropertyValue(@RequestBody List<SkuPropertyValue> skuPropertyValues) {
        JSONResultVo result = new JSONResultVo();
        try {
            skuPropertyService.saveOrUpdateSkuPropertyValues(skuPropertyValues);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
    @RequestMapping("/skuPropertyValue/delete/{skuPropertyId}")
    @ResponseBody
    public JSONResultVo deleteSkuPropertyValue(@PathVariable Long skuPropertyId) {
        JSONResultVo result = new JSONResultVo();
        try {
            skuPropertyService.deleteSkuPropertyValue(skuPropertyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
