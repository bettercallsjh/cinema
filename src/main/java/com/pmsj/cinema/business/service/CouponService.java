package com.pmsj.cinema.business.service;

/*
 * @Author 潘升
 * @Description //TODO 优惠券service$
 * @Date 2020/7/1 14:03
 **/

import com.pmsj.cinema.common.entity.Coupon;
import com.pmsj.cinema.common.entity.User;
import com.pmsj.cinema.common.entity.UserCoupon;
import com.pmsj.cinema.common.mapper.CouponMapper;
import com.pmsj.cinema.common.mapper.UserCouponMapper;
import com.pmsj.cinema.common.mapper.UserMapper;
import com.pmsj.cinema.common.vo.UserCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CouponService {

    @Autowired
    UserCouponMapper userCouponMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CouponMapper couponMapper;


    /**
     * @return int 1表示成功 2表余额不足 3表示异常
     * @Author 潘升
     * @Description //TODO 用户积分购买优惠劵
     * @Date 15:03 2020/7/1
     * @Param [couponid, userId, date, money]
     * @since 1.0.0
     */
    @Transactional(rollbackFor = Exception.class)
    public int addUserCoupon(Integer couponid, Integer userId, Date date, Integer money) {


        User user = userMapper.selectByPrimaryKey(userId);

        try {
            if (user.getUserPoints() >= money) {
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setCouponId(couponid);
                userCoupon.setUserId(userId);
                userCoupon.setCouponDate(date);
                userCoupon.setCouponStatus(1);
                userCouponMapper.insert(userCoupon);
                user.setUserPoints(user.getUserPoints() - money);
                userMapper.updateByPrimaryKey(user);
                return 1;
            }

            return 2;
        } catch (Exception e) {
            return 3;
        }

    }

    public List<UserCouponVo> getAllCoupon(int userId) {
        return userCouponMapper.selectAllByUserId(userId, new Date());
    }

    public List<UserCouponVo> getAllOverCoupon(int userId) {
        return userCouponMapper.getAllOverCoupon(userId, new Date());
    }


    /**
     * @return void
     * @Author 潘升
     * @Description //TODO
     * @Date 17:48 2020/7/9
     * @Param [userId, cinemaId, movieId]
     * @since 1.0.0
     */
    public List<Coupon> getUserAvailableCoupon(Integer userId, Integer cinemaId, Integer movieId) {

        return couponMapper.getUserAvailableCoupon(userId, cinemaId, movieId);
    }


}
