package com.pmsj.cinema.common.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/7 13:48
 **/
@Data
@ToString
public class AutocompleteVo<T> {
    private String msg;
    private Integer code;
    private List<T> data;
}
