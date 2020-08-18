package com.pmsj.cinema.business.exception;



/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class NullParametersException extends ServiceException {
    public NullParametersException() {
    }
    public NullParametersException(String msg) {
        super(msg);
    }

    public NullParametersException(Throwable cause) {
        super(cause);
    }

    public NullParametersException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
