package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.UserLogin;

/**
 * Created by WangZhe on 2018/8/20.
 */
public interface IUserLoginService {
    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    UserLogin register(String username, String password);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    String login(String username, String password);

    /**
     * 用户退出
     *
     * @param token
     */
    void logout(String token);
}
