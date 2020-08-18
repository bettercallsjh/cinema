package com.pmsj.cinema.business.exception;



/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class NotAvailableException extends ServiceException {
    public NotAvailableException() {
    }
    public NotAvailableException(String msg) {
        super(msg);
    }

    public NotAvailableException(Throwable cause) {
        super(cause);
    }

    public NotAvailableException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
