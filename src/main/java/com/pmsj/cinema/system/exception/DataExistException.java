package com.pmsj.cinema.system.exception;

/**
 * @author mhd
 * @className ServiceException
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class DataExistException extends ServiceException {
    public DataExistException() {
    }
    public DataExistException(String msg) {
        super(msg);
    }

    public DataExistException(Throwable cause) {
        super(cause);
    }

    public DataExistException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
