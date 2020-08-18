package com.pmsj.cinema.system.service;

import com.pmsj.cinema.business.exception.NotAvailableException;
import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.common.entity.Seat;
import com.pmsj.cinema.common.mapper.SeatMapper;
import com.pmsj.cinema.common.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mhd
 * @className BrandService
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
@Service
public class SeatSystemService {
    @Autowired(required = false)
    SeatMapper seatMapper;

    /**
     *
     * @param ticket
     * @param
     * @return
     */
    public Boolean isAvailable(TicketVo ticket, Integer hallid){
        if (ticket==null){
            throw new NullParametersException("blank is null");
        }
        Seat seat = seatMapper.selectByBlank(ticket.getCol(),ticket.getRow(),hallid);
        if (seat==null){
            return true;
        }else {
            throw new NotAvailableException("blank is exist");
        }
    }

    /**
     * 添加影厅空白布局
     * @param seat
     * @return
     */
    public int addBlank(Seat seat){
        if (seat==null){
            throw new NullParametersException("blank is null");
        }
        return seatMapper.insert(seat);
    }
    /**
     *
     */
    public int delByHallid(Integer hallId){
        if (hallId==null){
            throw new NullParametersException("hallId is null");
        }
        return seatMapper.deleteByHallId(hallId);
    }


}
