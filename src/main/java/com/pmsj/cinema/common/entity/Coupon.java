package com.pmsj.cinema.common.entity;

import lombok.ToString;

@ToString
public class Coupon {
    private Integer couponId;

    private String couponName;

    private Double couponMoeny;

    private Integer userCouponId;

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Double getCouponMoeny() {
        return couponMoeny;
    }

    public void setCouponMoeny(Double couponMoeny) {
        this.couponMoeny = couponMoeny;
    }
}