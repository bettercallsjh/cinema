package com.pmsj.cinema.system.exception;

/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class PageInfoErrorException extends ServiceException {
    public PageInfoErrorException() {
    }
    public PageInfoErrorException(String msg) {
        super(msg);
    }

    public PageInfoErrorException(Throwable cause) {
        super(cause);
    }

    public PageInfoErrorException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
