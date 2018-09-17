package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhe on 2018年08月19日.
 */
@Controller
public class ProductSkuController {
    @Reference
    private ISkuPropertyService skuPropertyService;
    @Reference
    private IProductSkuService productSkuService;
    @Reference
    private IProductService productService;
    @Reference
    private ISkuPropertyValueService skuPropertyValueService;

    @RequestMapping("/productSku")
    public String SkuPropertyPage(Model model, Long productId) {
        List<ProductSku> productSkus = productSkuService.selectProductSkusByProductId(productId);
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("skuPropertyList", skuPropertyService.selectSkuPropertiesByProductId(productId));
        if (productSkus.size() > 0) {
            model.addAttribute("skuList", productSkuService.selectProductSkusByProductId(productId));
            return "/product_sku/sku_list";
        }
        return "/product_sku/generate_sku";
    }

    @RequestMapping("/productSku/getSkuPropertyValue")
    public String SkuPropertyValuePage(Model model, Long skuPropertyId) {
        model.addAttribute("skuProperty", skuPropertyService.getBySkuPropertyId(skuPropertyId));
        model.addAttribute("skuPropertyValueList", skuPropertyValueService.selectSkuProperyValuesBySkuPropertyId(skuPropertyId));
        return "product_sku/sku_property_value_table";
    }

    @RequestMapping("/productSku/generateSku")
    public String generateSkuPage(@RequestBody GenerateSkuVo generateSkuVo, Model model) {
        List<Map<String, Object>> resultData = skuPropertyService.generateSku(generateSkuVo);
        model.addAttribute("skuPropertyList", generateSkuVo.getSkuPropertyList());
        model.addAttribute("skuList", resultData);
        return "product_sku/generate_sku_page";
    }

    @RequestMapping("/productSku/save")
    @ResponseBody
    public JSONResultVo saveSku(GenerateSkuVo generateSkuVo) {
        JSONResultVo result = new JSONResultVo();
        try {
            productSkuService.save(generateSkuVo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

}
