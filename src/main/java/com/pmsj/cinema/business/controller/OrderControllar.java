package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author mhd
 * @className OrderControllar
 * @description TODO
 * @create 2020/7/23
 * @since 1.0.0
 */
@RequestMapping("/order")
@Controller
public class OrderControllar {
    @Autowired
    OrderService orderService;

    @RequestMapping("/getTime")
    @ResponseBody
    public Date getTime(String orderNo){
        return orderService.getOrderByNo(orderNo).getOrderTime();
    }
}
