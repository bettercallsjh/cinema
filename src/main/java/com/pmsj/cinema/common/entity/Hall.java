package com.pmsj.cinema.common.entity;

public class Hall {
    private Integer hallId;

    private String hallName;

    private Integer hallType;

    private Integer hallStatus;

    private Integer hallX;

    private Integer hallY;

    private Integer cinemaId;

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
        this.hallName = hallName == null ? null : hallName.trim();
    }

    public Integer getHallType() {
        return hallType;
    }

    public void setHallType(Integer hallType) {
        this.hallType = hallType;
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
}