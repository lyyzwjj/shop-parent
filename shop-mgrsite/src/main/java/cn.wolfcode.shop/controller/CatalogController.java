package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by WangZhe on 2018年08月15日.
 */
@Controller
public class CatalogController {
    @Reference
    private ICatalogService catalogService;

    @RequestMapping("/catalog")
    public String sellecAll(Model model) {
        //model.addAttribute("allCatalogJson", JSON.toJSONString(catalogService.selectAll()));
        model.addAttribute("allCatalogJson", catalogService.selectAllFromCache());
        return "/catalog/catalog";
    }

    @RequestMapping("/catalog/getChildCatalog")
    public String childDatalogPage(Model model, Long     catalogId) {
        model.addAttribute("catalogList", catalogService.queryChildrenByParentId(catalogId));
        return "/catalog/child_catalog";
    }

    @RequestMapping("/catalog/updateSort")
    @ResponseBody
    public JSONResultVo updateSort(@RequestBody List<Long> ids) {
        JSONResultVo result = new JSONResultVo();
        try {
            catalogService.updateSort(ids);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/catalog/save")
    @ResponseBody
    public JSONResultVo saveOrUpdate(Catalog catalog) {
        JSONResultVo result = new JSONResultVo();
        try {
            catalogService.saveOrUpdate(catalog);

        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/catalog/delete")
    @ResponseBody
    public JSONResultVo delete(Long catalogId) {
        JSONResultVo result = new JSONResultVo();
        try {
            catalogService.delete(catalogId);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }


}
