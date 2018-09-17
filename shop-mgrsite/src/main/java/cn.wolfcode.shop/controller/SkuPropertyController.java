package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WangZhe on 2018年08月17日.
 */
@Controller
public class SkuPropertyController {
    @Reference
    private ICatalogService cataslogervice;
    @Reference
    private ISkuPropertyService skuPropertyService;

    @RequestMapping("skuProperty")
    public String skuPropertyPage(Model model) {
        model.addAttribute("allCatalog", cataslogervice.selectAllFromCache());
        return "/skuProperty/skuProperty";
    }

    @RequestMapping("/skuProperty/get/{catalogId}")
    public String skuPropertyListPage(Model model, @PathVariable Long catalogId) {
        model.addAttribute("skuPropertyList", skuPropertyService.selectSkuPropertyByCatalogId(catalogId));
        return "/skuProperty/skuProperty_list";
    }

    @RequestMapping("/skuProperty/save")
    @ResponseBody
    public JSONResultVo saveOrUpdate(SkuProperty skuProperty) {
        JSONResultVo result = new JSONResultVo();
        try {
            skuPropertyService.saveOrUpdate(skuProperty);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/skuProperty/delete/{skuPropertyId}")
    @ResponseBody
    public JSONResultVo delete(@PathVariable Long skuPropertyId) {
        JSONResultVo result = new JSONResultVo();
        try {
            skuPropertyService.delete(skuPropertyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
