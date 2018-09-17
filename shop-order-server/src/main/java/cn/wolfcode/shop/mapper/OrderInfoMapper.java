package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);
}