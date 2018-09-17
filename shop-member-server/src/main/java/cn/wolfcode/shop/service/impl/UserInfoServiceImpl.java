package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.mapper.UserInfoMapper;
import cn.wolfcode.shop.service.IUserInfoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WangZhe on 2018年08月20日.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

}
