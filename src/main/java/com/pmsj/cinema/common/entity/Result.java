package com.pmsj.cinema.common.entity;

import lombok.Data;
import lombok.ToString;

/*
 * @Author 潘升
 * @Description //TODO 处理服务器请求的实体类$
 * @Date 2020/6/4 8:33
 **/

@Data
@ToString
public class Result<T> {

    private String result;

    private String msg;

    private T data;

    private int total;

    private int available;

    /**
     * @return com.woniu.wms.entity.Result
     * @Author 潘升
     * @Description //TODO 成功的响应
     * @Date 8:41 2020/6/4
     * @Param [msg, data]
     * @since 1.0.0
     */
    public Result success(String msg, T data) {
        Result result = new Result();
        result.setResult("success");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * @return com.woniu.wms.entity.Result
     * @Author 潘升
     * @Description //TODO 成功的响应
     * @Date 8:41 2020/6/4
     * @Param [msg, data]
     * @since 1.0.0
     */
    public Result error(String msg, T data) {
        Result result = new Result();
        result.setResult("error");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}
