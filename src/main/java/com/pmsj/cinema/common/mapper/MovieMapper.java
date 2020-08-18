package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.vo.MovieBugAddVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper {
    int deleteByPrimaryKey(Integer movieId);

    int insert(Movie record);

    Movie selectByPrimaryKey(Integer movieId);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);

    List<Movie> selectIndexTopMovie(Integer status);

    List<Movie> selectOnline();

    List<Movie> selectTopTen(Integer status);

    List<Movie> selectComingSoon();

    List<Movie> getMovieByKeyWord(String keyWord);

    void insertByMovieBugAddVo(MovieBugAddVo movieBugAddVo);

    int getMaxId();

    List<Movie> selectAllMovie(@Param(value = "status") Integer status, @Param(value = "tId") Integer typeId, @Param(value = "area") String movieArea, @Param(value = "year") String movieReleaseTime, @Param(value = "paixu") Integer paixu);

    List<Movie> getMovieByMovieName(String movieName);

    List getMovieIsOnline(Integer movieId);

//    List<Movie> selectAllComingSoonMovie(@Param(value = "typeId") Integer typeId,@Param(value = "area")String movieArea,@Param(value = "year")Date movieReleaseTime);
//
//    List<Movie> selectAllOfflineMovie(@Param(value = "typeId") Integer typeId,@Param(value = "area")String movieArea,@Param(value = "year")Date movieReleaseTime);
}