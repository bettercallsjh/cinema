package com.pmsj.cinema.common.entity;

import java.util.Date;

public class UserCoupon {
    private Integer userCouponId;

    private Integer userId;

    private Integer couponId;

    private Integer couponStatus;

    private Date couponDate;

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Date getCouponDate() {
        return couponDate;
    }

    public void setCouponDate(Date couponDate) {
        this.couponDate = couponDate;
    }
}