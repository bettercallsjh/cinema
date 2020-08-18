package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Hall;
import com.pmsj.cinema.common.vo.HallVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HallMapper {
    int deleteByPrimaryKey(Integer hallId);

    int insert(Hall record);

    Hall selectByPrimaryKey(Integer hallId);

    List<Hall> selectAll();

    int updateByPrimaryKey(Hall record);

    List<Hall> getAllHallByCinemaId(Integer cinemaId);

    Hall isExist(@Param("hallName")String hallName,@Param("hallType") Integer hallType,@Param("cinemaId")Integer cinemaId);

    List<HallVo> getAllHall(Hall hall);

    HallVo getHallByHallId(Integer hallId);
}