package com.pmsj.cinema.business.controller;

/*
 * @author 潘升
 * @description //TODO 个人中心$
 * date 2020/7/1 11:02
 **/

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.business.service.OrderService;
import com.pmsj.cinema.business.service.UserService;
import com.pmsj.cinema.common.entity.Cinema;
import com.pmsj.cinema.common.entity.Coupon;
import com.pmsj.cinema.common.entity.User;
import com.pmsj.cinema.common.mapper.UserMapper;
import com.pmsj.cinema.common.vo.OrderVo;
import com.pmsj.cinema.system.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("PersonalCentreController")
public class PersonalCentreController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    CinemaService cinemaService;


    @RequestMapping("getUser")
    public User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userMapper.selectByPrimaryKey(user.getUserId());
    }


    @RequestMapping("getUserAllMoney")
    public double getUserAllMoney(HttpSession session) {

        User user = (User) session.getAttribute("user");
        Double userAllMoney = userService.getUserAllMoney(user.getUserId());
        return userAllMoney;
    }

    @RequestMapping("getAllOrderByUser")
    public PageInfo<OrderVo> getAllOrderByUser(Integer currentPage, Integer pageSize, HttpSession session) {

        if (currentPage == null) {
            currentPage = 1;
        }
        pageSize = 4;
        User user = (User) session.getAttribute("user");
        return orderService.getAllOrderByUser(user.getUserId(), currentPage, pageSize);
    }


    @RequestMapping("getUserAllOrderCount")
    public int getUserAllOrderCount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.getUserAllOrderCount(user.getUserId());
    }

    @RequestMapping("getOrderById")
    public OrderVo getOrderById(Integer id) {
        return orderService.getOrderById(id);
    }


    @RequestMapping("getCouponById")

    public Coupon getCouponById(Integer id) {
        return orderService.getCouponById(id);
    }


    @RequestMapping("getCinemaById")
    public Cinema getCinemaById(Integer id) {
        return cinemaService.getCinemaByOrderId(id);
    }

}
