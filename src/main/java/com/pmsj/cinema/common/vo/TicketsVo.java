package com.pmsj.cinema.common.vo;

import java.util.List;

/**
 * @author mhd
 * @className TicketsVo
 * @description TODO
 * @create 2020/7/9
 * @since 1.0.0
 */
public class TicketsVo {
    private Integer hallMovieId;
    private List<TicketVo> tickets;
    private Integer userCouponId;

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public Integer getHallMovieId() {
        return hallMovieId;
    }

    public void setHallMovieId(Integer hallMovieId) {
        this.hallMovieId = hallMovieId;
    }

    public List<TicketVo> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketVo> tickets) {
        this.tickets = tickets;
    }
}
