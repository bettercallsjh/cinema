package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Coupon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    Coupon selectByPrimaryKey(Integer couponId);

    List<Coupon> selectAll();

    int updateByPrimaryKey(Coupon record);

    List<Coupon> getUserAvailableCoupon(@Param("userId") Integer userId, @Param("cinemaId") Integer cinemaId, @Param("movieId") Integer movieId);

}