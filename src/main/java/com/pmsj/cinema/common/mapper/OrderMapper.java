package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Order;
import com.pmsj.cinema.common.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    List<OrderVo> getAllOrderByUser(int i);

    Order selectByOrderNo(String orderNo);

    List<Integer> getOnlyOrderIdByUserId(int i);

    List<OrderVo> getAllOrderByOrderId(@Param("onlyOrderIdByUserId") List<Integer> onlyOrderIdByUserId);

    OrderVo getOrderVoById(Integer id);
}