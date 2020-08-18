package com.pmsj.cinema.system.util;




/**
 * @author mhd
 * @className ReturnUtil
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
public class ReturnUtil<T> {
    private Integer code;
    private String msg;
    private Long count;
    private T data;

    public ReturnUtil() {
    }

    public ReturnUtil(Integer code, String msg, Long count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ReturnUtil failure(){
        ReturnUtil returnUtil = new ReturnUtil(500,"failure",null,null);
        return returnUtil;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
