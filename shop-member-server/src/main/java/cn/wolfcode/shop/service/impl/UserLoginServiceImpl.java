package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.UserInfo;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.mapper.UserInfoMapper;
import cn.wolfcode.shop.mapper.UserLoginMapper;
import cn.wolfcode.shop.service.IUserLoginService;
import cn.wolfcode.shop.util.MD5;
import cn.wolfcode.shop.util.RedisConstants;
import com.alibaba.dubbo.config.annotation.Service;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by WangZhe on 2018年08月20日.
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserLogin register(String username, String password) {
        int count = userLoginMapper.queryCountByUsername(username);
        if (count > 0) {
            throw new UserException("该用户名已被注册,请重新输入");
        }
        userLoginMapper.register(username, MD5.encode(password));
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(MD5.encode(password));
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userLogin.getId());
        userInfo.setRegistTime(new Date());
        userInfoMapper.insert(userInfo);
        return userLogin;
    }

    @Override
    public String login(String username, String password) {
        UserLogin userLogin = userLoginMapper.checkLogin(username, MD5.encode(password));
        if (userLogin == null) {
            throw new UserException("账号密码错误,请重新输入");
        }
        String token = creatToken(userLogin);
        return token;

    }

    private String creatToken(UserLogin userLogin) {
        String token = UUID.randomUUID().toString();
        String userKey = MessageFormat.format(RedisConstants.USER_LOGIN_TOKEN, token);
        redisTemplate.opsForValue().set(userKey, userLogin);
        redisTemplate.expire(userKey, 30, TimeUnit.DAYS);
        return token;
    }

    public void logout(String token) {
        String userKey = MessageFormat.format(RedisConstants.USER_LOGIN_TOKEN, token);
        redisTemplate.delete(userKey);
    }
}
