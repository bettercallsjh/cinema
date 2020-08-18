package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.OrderSeat;
import java.util.List;

public interface OrderSeatMapper {
    int insert(OrderSeat record);

    List<OrderSeat> selectAll();
    List<OrderSeat> selectByOrderId(Integer orderId);
}