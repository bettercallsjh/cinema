package com.pmsj.cinema.business.service;

import com.pmsj.cinema.business.exception.NotAvailableException;
import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.business.util.ReflectUtil;
import com.pmsj.cinema.common.entity.Seat;
import com.pmsj.cinema.common.mapper.SeatMapper;
import com.pmsj.cinema.common.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mhd
 * @className HallMovieService
 * @description TODO
 * @create 2020/7/7
 * @since 1.0.0
 */
@Service
public class SeatService {
    @Autowired(required = false)
    SeatMapper seatMapper;

    /**
     * 得到影厅布局
     * @param hallId
     * @return
     */
    public List<Seat> getNonSeat(Integer hallId) {
        if (hallId==null){
            throw new NullParametersException("HallId is null");
        }
        return seatMapper.selectByHallId(hallId);
    }

    /**
     * 得到已被购买座位
     * @param hallMovieId
     * @return
     */
    public List<Seat> getSelectedSeat(Integer hallMovieId) {
        if (hallMovieId==null){
            throw new NullParametersException("HallMovieId is null");
        }
        return seatMapper.selectByHallMovieId(hallMovieId);
    }

    /**
     * 添加已被购买座位
     * @param seat
     * @return
     */
    public int addSeat(Seat seat){
        String[] strings = {"seatId","hallId"};
        if (seat==null){
            throw new NullParametersException("Seat is null");
        }else {
            new ReflectUtil<Seat>().throwNullParametersException(seat,strings);
        }
        return seatMapper.insert(seat);
    }

    /**
     * 判断座位是否可用
     */
    public Boolean isAvailable(TicketVo ticket,Integer hallMovieId){
        if (ticket==null){
            throw new NullParametersException("Ticket is null");
        }else {
            new ReflectUtil<TicketVo>().throwNullParametersException(ticket,null);
        }
        Seat seat = seatMapper.selectByRowAndCol(ticket.getCol(),ticket.getRow(),hallMovieId);
        if (seat==null){
            return true;
        }else {
            throw new NotAvailableException("Seat is not available");
        }
    }

}
