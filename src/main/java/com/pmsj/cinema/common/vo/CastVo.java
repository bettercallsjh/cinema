package com.pmsj.cinema.common.vo;

import lombok.Data;
import lombok.ToString;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/7 19:18
 **/
@Data
@ToString
public class CastVo {
    private String castName;
    private String castRole;
    private String type;
    private Integer castId;
}
