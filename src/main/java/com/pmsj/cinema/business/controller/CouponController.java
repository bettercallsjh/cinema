package com.pmsj.cinema.business.controller;

/*
 * @Author 潘升
 * @Description //TODO 优惠劵$
 * @Date 2020/7/1 13:57
 **/

import com.pmsj.cinema.business.service.CouponService;
import com.pmsj.cinema.common.entity.Result;
import com.pmsj.cinema.common.vo.UserCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("CouponController")
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("shopCoupon")
    public Result shopCoupon(Integer id, Integer money) {

        Date date = new Date();
        long time = date.getTime();
        date.setTime(time + (1000 * 60 * 60 * 24 * 2));

        int i = couponService.addUserCoupon(id, 1, date, money);

        if (i == 1) {
            return new Result().success("购买成功", null);
        } else if (i == 2) {
            return new Result().success("余额不足", null);
        } else {
            return new Result().success("系统维护中。。。", null);
        }

    }

    @RequestMapping("getAllCoupon")
    public List<UserCouponVo> getAllCoupon() {

        int userId = 1;
        return couponService.getAllCoupon(userId);
    }


    @RequestMapping("getAllOverCoupon")
    public List<UserCouponVo> getAllOverCoupon() {

        int userId = 1;
        return couponService.getAllOverCoupon(userId);
    }

}
