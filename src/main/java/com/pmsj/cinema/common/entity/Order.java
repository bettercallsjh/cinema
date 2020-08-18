package com.pmsj.cinema.common.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderId;

    private String orderNo;

    private BigDecimal orderTotalDiscountsCash;

    private Integer couponId;

    private Integer userCouponId;

    private Integer userId;

    private Integer orderStatus;

    private Integer orderCount;

    private BigDecimal orderTotalInitialCash;

    private BigDecimal orderUnitprice;

    private Date orderTime;

    private Integer hallMovieId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getOrderTotalDiscountsCash() {
        return orderTotalDiscountsCash;
    }

    public void setOrderTotalDiscountsCash(BigDecimal orderTotalDiscountsCash) {
        this.orderTotalDiscountsCash = orderTotalDiscountsCash;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderTotalInitialCash() {
        return orderTotalInitialCash;
    }

    public void setOrderTotalInitialCash(BigDecimal orderTotalInitialCash) {
        this.orderTotalInitialCash = orderTotalInitialCash;
    }

    public BigDecimal getOrderUnitprice() {
        return orderUnitprice;
    }

    public void setOrderUnitprice(BigDecimal orderUnitprice) {
        this.orderUnitprice = orderUnitprice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getHallMovieId() {
        return hallMovieId;
    }

    public void setHallMovieId(Integer hallMovieId) {
        this.hallMovieId = hallMovieId;
    }

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }
}