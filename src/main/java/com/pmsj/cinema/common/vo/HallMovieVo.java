package com.pmsj.cinema.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/6 11:48
 **/
@Data
@ToString
public class HallMovieVo {

    private Integer hallMovieId;

    private Integer movieId;

    private Integer hallId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    private Double fareMoney;

    private String movieName;

    private String movieImg;

    private String hallName;

    private String hallTypeName;

}
