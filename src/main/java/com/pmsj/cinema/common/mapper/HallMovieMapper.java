package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.HallMovie;
import com.pmsj.cinema.common.vo.HallMovieVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HallMovieMapper {
    List<HallMovieVo> getHallMovies(@Param("cinemaId") Integer cinemaId, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("movieId") Integer movieId);

    int deleteByPrimaryKey(Integer hallMovieId);

    int insert(HallMovie record);

    HallMovie selectByPrimaryKey(Integer hallMovieId);

    List<HallMovie> selectAll();

    int updateByPrimaryKey(HallMovie record);

    int getHallMoviesByTime(@Param("hallid") Integer hallid, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Date getRecentTime(@Param("cinemaId") Integer cinemaId, @Param("movieId") Integer movieId);
}