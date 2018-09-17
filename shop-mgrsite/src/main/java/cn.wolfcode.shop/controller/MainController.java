package cn.wolfcode.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WangZhe on 2018年08月18日.
 */
@Controller
public class MainController {
    @RequestMapping("/main")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:main";
    }

    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }
}
