package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.mapper.InvoiceMapper;
import cn.wolfcode.shop.mapper.OrderInfoMapper;
import cn.wolfcode.shop.mapper.OrderProductMapper;
import cn.wolfcode.shop.message.OrderConsumer;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.service.IUserAddressService;
import cn.wolfcode.shop.util.IdGenerateUtil;
import cn.wolfcode.shop.util.RedisConstants;
import cn.wolfcode.shop.vo.GenerateOrderVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by WangZhe on 2018年08月23日.
 */

@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Reference
    private IUserAddressService userAddressService;

    public OrderInfo generateOrder(String token, GenerateOrderVo generateOrderVo) {
        //通过token获取当前登录用户
        String userKey = MessageFormat.format(RedisConstants.USER_LOGIN_TOKEN, token);
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(userKey);
        if (userLogin == null) {
            throw new UserException("非法操作");
        }
        //创建订单对象，设置用户id
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userLogin.getId());
        //生成订单编号
        orderInfo.setOrderSn(IdGenerateUtil.get().nextId() + "");
        //通过用户收货地址id获取用户收货地址对象，并设置到订单信息中
        UserAddress userAddress = userAddressService.get(generateOrderVo.getUserAddressId());
        orderInfo.setPhone(userAddress.getPhone());
        orderInfo.setCountry(userAddress.getCountry());
        orderInfo.setProvince(userAddress.getProvince());
        orderInfo.setCity(userAddress.getCity());
        orderInfo.setDistrict(userAddress.getDistrict());
        orderInfo.setAddress(userAddress.getAddress());
        orderInfo.setZipcode(userAddress.getZipcode());
        orderInfo.setConsignee(userAddress.getConsignee());
        //设置订单的其他信息，比如订单状态，收货状态，物流状态，下单时间等
        orderInfo.setOrderStatus(0);
        orderInfo.setFlowStatus(0);
        orderInfo.setPayStatus(0);
        orderInfo.setPayType(generateOrderVo.getPay().getPayType());
        orderInfo.setOrderTime(new Date());
        //设置订单总金额
        orderInfo.setOrderAmount(new BigDecimal("0"));
        //插入该订单
        orderInfoMapper.insert(orderInfo);
        //获取购物车商品列表遍历，并生成订单商品明细
        List<Long> productSkuIds = generateOrderVo.getProductSkuIds();
        String shopCarKey;
        ShopCar shopCar;
        OrderProduct orderProduct;
        BigDecimal orderAmount = new BigDecimal("0");
        List<String> shopCarKeyList = new ArrayList<>();
        //shopCar的信息转移到订单商品明细中
        //通过skuId获取sku对象，并设置价格
        //计算订单商品明细中的商品小计
        //把订单商品明细中的商品小计累加到订单总额中
        //插入该订单商品明细
        //遍历sku属性集合，并设置到订单商品明细属性对象中，插入该订单商品明细属性数据
        //判断有无购物车id，如果有则删除购物车信息
        //从新设置订单总额
        for (Long productSkuId : productSkuIds) {
            shopCarKey = MessageFormat.format(RedisConstants.USER_SHOP_CAR, userLogin.getId(), productSkuId);
            shopCar = (ShopCar) redisTemplate.opsForValue().get(shopCarKey);
            if (shopCar == null) {
                throw new UserException("非法操作.");
            }
            shopCarKeyList.add(shopCarKey);
            orderProduct = new OrderProduct();
            orderProduct.setOrderId(orderInfo.getId());
            orderProduct.setSkuId(productSkuId);
            orderProduct.setProductImg(shopCar.getProductImage());
            orderProduct.setProductName(shopCar.getProductName());
            orderProduct.setProductNumber(shopCar.getNumber());
            orderProduct.setProductPrice(shopCar.getProductPrice());
            orderProduct.setSkuType(shopCar.getProductSkuPropertyStr());
            orderProduct.setTotalPrice(shopCar.getProductPrice().multiply(shopCar.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            orderAmount.add(orderProduct.getTotalPrice());
            orderProductMapper.insert(orderProduct);
        }
        //更新该订单
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //判断是否需要开发票，如果要则,设置发票人和发票对应的订单，并插入发票信息
        Invoice invoice = generateOrderVo.getInvoice();
        if (invoice != null) {
            invoice.setUserId(userLogin.getId());
            invoice.setOrderId(orderInfo.getId());
            invoiceMapper.insert(invoice);
        }
        for (String key : shopCarKeyList) {
            redisTemplate.delete(key);
        }
        return orderInfo;
    }
}

/*
 //通过token获取当前登录用户
        String userKey = MessageFormat.format(RedisConstants.USER_LOGIN_TOKEN, token);
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(userKey);
        if (userLogin == null) {
            throw new UserException("用户信息过期了.");
        }
        OrderInfo orderInfo = new OrderInfo();
        //创建订单对象，设置用户id
        orderInfo.setUserId(userLogin.getId());
        //生成订单编号
        orderInfo.setOrderSn("PO0010001xxx");
        //通过用户收货地址id获取用户收货地址对象，并设置到订单信息中
        UserAddress userAddress = userAddressService.get(generateOrderVo.getUserAddressId());
        orderInfo.setAddress(userAddress.getAddress());
        orderInfo.setCity(userAddress.getCity());
        orderInfo.setConsignee(userAddress.getConsignee());
        orderInfo.setCountry(userAddress.getCountry());
        orderInfo.setDistrict(userAddress.getDistrict());
        orderInfo.setPhone(userAddress.getPhone());
        orderInfo.setProvince(userAddress.getProvince());
        orderInfo.setZipcode(userAddress.getZipcode());
        //设置订单的其他信息，比如订单状态，收货状态，物流状态，下单时间等
        orderInfo.setOrderStatus(0);
        orderInfo.setFlowStatus(0);
        orderInfo.setPayType(0);
        orderInfo.setOrderTime(new Date());
        //设置订单总金额
        orderInfo.setOrderAmount(new BigDecimal(0));
        //插入该订单
        orderInfoMapper.insert(orderInfo);
        //获取购物车商品列表遍历，并生成订单商品明细
        OrderProduct orderProduct;
        ShopCar shopCar;
        String shopCarKey;
        List<String> shopCarKeyList = new ArrayList();
        for (Long productSkuId : generateOrderVo.getProductSkuIds()) {
            orderProduct = new OrderProduct();
            shopCarKey = MessageFormat.format(RedisConstants.USER_SHOP_CAR, userLogin.getId(), productSkuId);
            shopCarKeyList.add(shopCarKey);
            shopCar = (ShopCar) redisTemplate.opsForValue().get(shopCarKey);
            if (shopCar == null) {
                throw new UserException("非法操作.");
            }
            //car的信息转移到订单商品明细中
            //通过skuId获取sku对象，并设置价格
            //计算订单商品明细中的商品小计
            //把订单商品明细中的商品小计累加到订单总额中
            //插入该订单商品明细
            orderProduct.setOrderId(orderInfo.getId());
            orderProduct.setProductImg(shopCar.getProductImage());
            orderProduct.setProductName(shopCar.getProductName());
            orderProduct.setProductNumber(shopCar.getNumber());
            orderProduct.setProductPrice(shopCar.getProductPrice());
            orderProduct.setSkuId(productSkuId);
            orderProduct.setSkuType(shopCar.getProductSkuPropertyStr());
            orderProduct.setTotalPrice(shopCar.getProductPrice().multiply(shopCar.getNumber()));
            orderProductMapper.insert(orderProduct);
            orderInfo.setOrderAmount(orderInfo.getOrderAmount().add(orderProduct.getTotalPrice()));
        }
        //从新设置订单总额
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //更新该订单
        Invoice invoice = generateOrderVo.getInvoice();
        if (invoice != null) {
            //判断是否需要开发票，如果要则,设置发票人和发票对应的订单，并插入发票信息
            invoice.setUserId(userLogin.getId());
            invoice.setOrderId(orderInfo.getId());
            invoiceMapper.insert(invoice);
        }
        //判断有无购物车id，如果有则删除购物车信息
        for (String key : shopCarKeyList) {
            redisTemplate.delete(key);
        }
        return orderInfo;
 */