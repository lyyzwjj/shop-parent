package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.service.IUserLoginService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Retention;

/**
 * Created by WangZhe on 2018年08月20日.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Reference
    private IUserLoginService userLoginService;

    @PostMapping
    public JSONResultVo register(String username, String password) {
        JSONResultVo vo = new JSONResultVo();
        try {
            UserLogin userLogin = userLoginService.register(username, password);
            vo.setResult(userLogin);
        } catch (UserException e) {
            e.printStackTrace();
            vo.setErrorMsg(e.getMessage());
        }
        return vo;
    }

    @PostMapping("/login")
    public JSONResultVo login(String username, String password) {
        JSONResultVo result = new JSONResultVo();
        try {
            String token = userLoginService.login(username, password);
            result.setResult(token);
        } catch (UserException e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @DeleteMapping
    public JSONResultVo logout(@RequestHeader(value = "token") String token, HttpServletResponse response) {
        JSONResultVo result = new JSONResultVo();
        if (StringUtils.isEmpty(token)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.setErrorMsg("参数有误");
            return result;
        }
        try {
            userLoginService.logout(token);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (UserException e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
