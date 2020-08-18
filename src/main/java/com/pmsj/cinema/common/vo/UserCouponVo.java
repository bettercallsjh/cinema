package com.pmsj.cinema.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pmsj.cinema.common.entity.Condition;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/1 20:08
 **/
@Data
@ToString
public class UserCouponVo {
    private Integer userId;

    private Integer couponId;

    private Integer couponStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date couponDate;

    private Integer conditionId;

    private Integer movieId;

    private Integer cinemaId;

    private String couponName;

    private Long couponMoeny;

    private String cinemaName;

    private String movieName;


}
