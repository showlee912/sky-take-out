package com.sky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.AddressBook;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.entity.ShoppingCart;
import com.sky.exception.AddressBookBusinessException;
import com.sky.exception.OrderBusinessException;
import com.sky.exception.ShoppingCartBusinessException;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.OrderService;
import com.sky.utils.WeChatPayUtil;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
//    @Autowired
//    private OrderDetailMapper orderDetailMapper;
//    @Autowired
//    private ShoppingCartMapper shoppingCartMapper;
//    @Autowired
//    private AddressBookMapper addressBookMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatPayUtil weChatPayUtil;


    /**
     * 用户下单
     *
     * @param ordersSubmitDTO
     * @return
     */
    @Transactional
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {
//        //异常情况的处理（收货地址为空、超出配送范围、购物车为空）
//        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
//        if (addressBook == null) {
//            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
//        }
//
//        Long userId = BaseContext.getCurrentId();
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setUserId(userId);
//
//        //查询当前用户的购物车数据
//        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
//        if (shoppingCartList == null || shoppingCartList.size() == 0) {
//            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
//        }
//
//        //构造订单数据
//        Orders order = new Orders();
//        BeanUtils.copyProperties(ordersSubmitDTO,order);
//        order.setPhone(addressBook.getPhone());
//        order.setAddress(addressBook.getDetail());
//        order.setConsignee(addressBook.getConsignee());
//        order.setNumber(String.valueOf(System.currentTimeMillis()));
//        order.setUserId(userId);
//        order.setStatus(Orders.PENDING_PAYMENT);
//        order.setPayStatus(Orders.UN_PAID);
//        order.setOrderTime(LocalDateTime.now());
//
//        //向订单表插入1条数据
//        orderMapper.insert(order);
//
//        //订单明细数据
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        for (ShoppingCart cart : shoppingCartList) {
//            OrderDetail orderDetail = new OrderDetail();
//            BeanUtils.copyProperties(cart, orderDetail);
//            orderDetail.setOrderId(order.getId());
//            orderDetailList.add(orderDetail);
//        }
//
//        //向明细表插入n条数据
//        orderDetailMapper.insertBatch(orderDetailList);
//
//        //清理购物车中的数据
//        shoppingCartMapper.deleteByUserId(userId);
//
//        //封装返回结果
//        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
//                .id(order.getId())
//                .orderNumber(order.getNumber())
//                .orderAmount(order.getAmount())
//                .orderTime(order.getOrderTime())
//                .build();
//
//        return orderSubmitVO;
        return null;
    }



    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    public OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception {
//        // 当前登录用户id
//        Long userId = BaseContext.getCurrentId();
//        User user = userMapper.getById(userId);
//
//        //调用微信支付接口，生成预支付交易单
//        JSONObject jsonObject = weChatPayUtil.pay(
//                ordersPaymentDTO.getOrderNumber(), //商户订单号
//                new BigDecimal(0.01), //支付金额，单位 元
//                "苍穹外卖订单", //商品描述
//                user.getOpenid() //微信用户的openid
//        );
//
//        if (jsonObject.getString("code") != null && jsonObject.getString("code").equals("ORDERPAID")) {
//            throw new OrderBusinessException("该订单已支付");
//        }
//
//        OrderPaymentVO vo = jsonObject.toJavaObject(OrderPaymentVO.class);
//        vo.setPackageStr(jsonObject.getString("package"));
//
//        return vo;
        return null;
    }

    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo
     */
    public void paySuccess(String outTradeNo) {
        // 当前登录用户id
        Long userId = BaseContext.getCurrentId();

        // 根据订单号查询当前用户的订单
        Orders ordersDB = orderMapper.getByNumberAndUserId(outTradeNo, userId);

        // 根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();

        orderMapper.update(orders);
    }

}
