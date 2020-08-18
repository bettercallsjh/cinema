package com.pmsj.cinema.common.vo;

/**
 * @author mhd
 * @className TicketsVo
 * @description TODO
 * @create 2020/7/9
 * @since 1.0.0
 */
public class TicketVo {
   private Integer row;
   private Integer col;
   private String seatInfo;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}
