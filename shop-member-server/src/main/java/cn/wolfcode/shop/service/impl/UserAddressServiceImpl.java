package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.UserAddress;
import cn.wolfcode.shop.mapper.UserAddressMapper;
import cn.wolfcode.shop.service.IUserAddressService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by WangZhe on 2018年08月26日.
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public UserAddress get(Long userId) {
        return userAddressMapper.selectByUserId(userId);
    }
}
