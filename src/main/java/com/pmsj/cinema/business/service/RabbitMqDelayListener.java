package com.pmsj.cinema.business.service;

import com.pmsj.cinema.business.exception.CouponIllegalException;
import com.pmsj.cinema.common.entity.Order;
import com.pmsj.cinema.common.entity.OrderSeat;
import com.pmsj.cinema.common.entity.UserCoupon;
import com.pmsj.cinema.common.mapper.OrderSeatMapper;
import com.pmsj.cinema.common.mapper.SeatMapper;
import com.pmsj.cinema.common.mapper.UserCouponMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mhd
 * @className RabbitMqDelayListener
 * @description TODO
 * @create 2020/7/17
 * @since 1.0.0
 */
    @Component
    public class RabbitMqDelayListener {
        @Autowired
        OrderService orderService;
        @Autowired(required = false)
        SeatMapper seatMapper;
        @Autowired(required = false)
        OrderSeatMapper orderSeatMapper;
        @Autowired(required = false)
        UserCouponMapper userCouponMapper;

        private static final String DEAD_LETTER_QUEUE="cinema_dead_letter_queue";


        @Transactional
        @RabbitListener(queues = DEAD_LETTER_QUEUE)
        public void recv(String orderNo){

           Order order =  orderService.getOrderByNo(orderNo);
           //修改订单状态
            if (order.getOrderStatus()==1){
                order.setOrderStatus(0);
            }
            orderService.updateOrderStatues(order);
            //删除选择座位
            List<OrderSeat> orderSeats =orderSeatMapper.selectByOrderId(order.getOrderId());
            for (OrderSeat orderSeat : orderSeats) {
                seatMapper.deleteByPrimaryKey(orderSeat.getSeatId());
            }
            //退回优惠卷
            UserCoupon userCoupon =  userCouponMapper.selectByPrimaryKey(order.getUserCouponId());
            if(userCoupon!=null) {
            if (userCoupon.getCouponStatus()==0){
                userCoupon.setCouponStatus(1);
                userCouponMapper.updateByPrimaryKey(userCoupon);
            }else {
                throw new CouponIllegalException("Coupon is available");
            }
            }
        }
    }


