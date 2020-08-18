package com.pmsj.cinema.business.exception;



/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class CouponIllegalException extends ServiceException {
    public CouponIllegalException() {
    }
    public CouponIllegalException(String msg) {
        super(msg);
    }

    public CouponIllegalException(Throwable cause) {
        super(cause);
    }

    public CouponIllegalException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
