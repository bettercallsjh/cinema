package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.UserCoupon;
import com.pmsj.cinema.common.vo.UserCouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserCouponMapper {
    int deleteByPrimaryKey(Integer userCouponId);

    int insert(UserCoupon record);

    UserCoupon selectByPrimaryKey(Integer userCouponId);

    List<UserCoupon> selectAll();

    int updateByPrimaryKey(UserCoupon record);

    List<UserCouponVo> selectAllByUserId(@Param("userId") int userId, @Param("date") Date date);

    List<UserCouponVo> getAllOverCoupon(@Param("userId") int userId, @Param("date") Date date);

}