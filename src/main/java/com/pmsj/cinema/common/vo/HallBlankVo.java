package com.pmsj.cinema.common.vo;

import java.util.List;

public class HallBlankVo {
    private Integer hallId;

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    private String hallName;

    private Integer hallType;

    private Integer hallX;

    private Integer hallY;

    private Integer cinemaId;

    private List<TicketVo> tickets;

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getHallType() {
        return hallType;
    }

    public void setHallType(Integer hallType) {
        this.hallType = hallType;
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

    public List<TicketVo> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketVo> tickets) {
        this.tickets = tickets;
    }
}