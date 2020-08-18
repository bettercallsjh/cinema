package com.pmsj.cinema.common.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HallMovie {
    private Integer hallMovieId;

    private Integer movieId;

    private Integer hallId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private Date endTime;

    private double fareMoney;

    public Integer getHallMovieId() {
        return hallMovieId;
    }

    public void setHallMovieId(Integer hallMovieId) {
        this.hallMovieId = hallMovieId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getFareMoney() {
        return fareMoney;
    }

    public void setFareMoney(double fareMoney) {
        this.fareMoney = fareMoney;
    }
}