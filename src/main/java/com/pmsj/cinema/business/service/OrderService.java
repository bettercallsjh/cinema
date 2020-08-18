package com.pmsj.cinema.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.business.util.ReflectUtil;
import com.pmsj.cinema.common.entity.Coupon;
import com.pmsj.cinema.common.entity.Order;
import com.pmsj.cinema.common.mapper.CouponMapper;
import com.pmsj.cinema.common.mapper.OrderMapper;
import com.pmsj.cinema.common.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/2 16:44
 **/
@Service

public class OrderService {

    @Autowired(required = false)
    OrderMapper orderMapper;

    @Autowired
    CouponMapper couponMapper;
//    @Autowired
//    HallMovieService hallMovieService;

    public PageInfo<OrderVo> getAllOrderByUser(int i, int currentPage, int pageSize) {


        PageHelper.startPage(currentPage, pageSize);
        List<Integer> onlyOrderIdByUserId = orderMapper.getOnlyOrderIdByUserId(i);
        PageInfo<Integer> integerPageInfo = new PageInfo<>(onlyOrderIdByUserId);
        System.out.println(onlyOrderIdByUserId);
        List<OrderVo> allOrderByUser = orderMapper.getAllOrderByOrderId(onlyOrderIdByUserId);
        PageInfo<OrderVo> orderVoPageInfo = new PageInfo<>(allOrderByUser);
        orderVoPageInfo.setEndRow(integerPageInfo.getEndRow());
        orderVoPageInfo.setNextPage(integerPageInfo.getNextPage());
        orderVoPageInfo.setPrePage(integerPageInfo.getPrePage());
        orderVoPageInfo.setSize(integerPageInfo.getSize());
        orderVoPageInfo.setPageNum(integerPageInfo.getPageNum());
        orderVoPageInfo.setPages(integerPageInfo.getPages());
        orderVoPageInfo.setPageSize(integerPageInfo.getPageSize());
        orderVoPageInfo.setPrePage(integerPageInfo.getPrePage());

        return orderVoPageInfo;
    }

    /**
     * 产生订单
     *
     * @param order
     * @return
     */
    public int buyTickets(Order order) {
        String[] params = {"orderId", "couponId", "userCouponId"};
        if (order == null) {
            throw new NullParametersException("Order is null");
        } else {
            new ReflectUtil<Order>().throwNullParametersException(order, params);
        }

        return orderMapper.insert(order);
    }

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo
     * @return
     */
    public Order getOrderByNo(String orderNo) {
        if (orderNo == null) {
            throw new NullParametersException("OrderNo is null");
        }
        return orderMapper.selectByOrderNo(orderNo);
    }

    public int updateOrderStatues(Order order) {
        String[] params = {"couponId", "userCouponId"};
        if (order == null) {
            throw new NullParametersException("Order is null");
        } else {
            new ReflectUtil<Order>().throwNullParametersException(order, params);
        }
        return orderMapper.updateByPrimaryKey(order);

    }

    public OrderVo getOrderById(Integer id) {
        return orderMapper.getOrderVoById(id);
    }

    public Coupon getCouponById(Integer id) {
        return couponMapper.selectByPrimaryKey(id);

    }

//    public void buy2(TicketsVo tickets, HttpSession session){
//        hallMovieService.buyTicket( tickets, session);
//    }

}
