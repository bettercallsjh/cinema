package com.pmsj.cinema.common.vo;

public class HallVo {
    private Integer hallId;

    private String hallName;

    private Integer hallStatus;

    private Integer hallX;

    private Integer hallY;

    private Integer htId;

    private String htName;

    private Integer cinemaId;

    private String cinemaName;


    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(Integer hallStatus) {
        this.hallStatus = hallStatus;
    }

    public Integer getHallX() {
        return hallX;
    }

    public void setHallX(Integer hallX) {
        this.hallX = hallX;
    }

    public Integer getHallY() {
        return hallY;
    }

    public void setHallY(Integer hallY) {
        this.hallY = hallY;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    public String getHtName() {
        return htName;
    }

    public void setHtName(String htName) {
        this.htName = htName;
    }
}