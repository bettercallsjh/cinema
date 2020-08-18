package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeatMapper {
    int deleteByPrimaryKey(Integer seatId);

    int insert(Seat record);

    Seat selectByPrimaryKey(Integer seatId);

    List<Seat> selectAll();

    int updateByPrimaryKey(Seat record);

    List<Seat> selectByHallId(Integer hallId);

    List<Seat> selectByHallMovieId(Integer hallMovieId);

    Seat selectByRowAndCol(@Param("col") Integer col,@Param("row") Integer row,@Param("hallMovieId") Integer hallMovieId);
    Seat selectByBlank(@Param("col") Integer col,@Param("row") Integer row,@Param("hallId") Integer hallId);

    int deleteByHallId(Integer hallId);
}