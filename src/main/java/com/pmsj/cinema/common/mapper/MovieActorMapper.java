package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.MovieActor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorMapper {
    int deleteByPrimaryKey(Integer movieId);

    int insert(MovieActor record);

    MovieActor selectByPrimaryKey(Integer movieId);

    List<MovieActor> selectAll();

    int updateByPrimaryKey(MovieActor record);

    List<MovieActor> selectActor(Integer movieId);

    List<MovieActor> selectDirector(Integer movieId);

    List<MovieActor> getCast0AllByMovieId(Integer movieId);

    List<MovieActor> getCast1AllByMovieId(Integer movieId);

    void delByMovie(Integer movieMapperMaxId);
}