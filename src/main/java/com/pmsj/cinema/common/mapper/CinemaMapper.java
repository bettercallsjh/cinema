package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Cinema;
import com.pmsj.cinema.common.entity.HallMovie;
import com.pmsj.cinema.common.vo.CinemaVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    List<Cinema> selectAll();

    int updateByPrimaryKey(Cinema record);

    List<CinemaVo> getAllCinemaVo(Cinema cinema);   /* String brand, String hallType, String area, String province, String city*/

    List<CinemaVo> getAllCinemaByAll(@Param("brand") String brand, @Param("hallType") String hallType, @Param("area") String area, @Param("province") String province, @Param("city") String city);


    List<CinemaVo> getAllCinemaByMovie(@Param("brand") String brand, @Param("hallType") String hallType, @Param("area") String area, @Param("province") String province, @Param("city") String city, @Param("movieId") Integer movieId, @Param("date") Date date);


    Double getMinMoneyByCinemaId(Integer cinemaId);

    int getCountCinemaByLngAndLat(@Param("cinemaLat") String cinemaLat, @Param("cinemaLng") String cinemaLng);

    Cinema getCinemaByOrderId(Integer id);
}