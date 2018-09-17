package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IPropertyService;
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
 * Created by WangZhe on 2018年08月17日.
 */
@Controller
public class PropertyController {
    @Reference
    private ICatalogService cataslogervice;
    @Reference
    private IPropertyService propertyService;

    @RequestMapping("property")
    public String propertyPage(Model model) {
        model.addAttribute("allCatalog", cataslogervice.selectAllFromCache());
        return "/property/property";
    }

    @RequestMapping("/property/get/{catalogId}")
    public String propertyListPage(Model model, @PathVariable Long catalogId) {
        model.addAttribute("propertyList", propertyService.selectPropertyByCatalogId(catalogId));
        return "/property/property_list";
    }

    @RequestMapping("/property/save")
    @ResponseBody
    public JSONResultVo saveOrUpdate(Property property) {
        JSONResultVo result = new JSONResultVo();
        try {
            propertyService.saveOrUpdate(property);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/property/delete/{propertyId}")
    @ResponseBody
    public JSONResultVo delete(@PathVariable Long propertyId) {
        JSONResultVo result = new JSONResultVo();
        try {
            propertyService.delete(propertyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
