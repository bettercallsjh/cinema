package com.pmsj.cinema.system.exception;

/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class ControllerException extends RuntimeException {
    public ControllerException() {

    }
    public ControllerException(String msg) {
        super(msg);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
