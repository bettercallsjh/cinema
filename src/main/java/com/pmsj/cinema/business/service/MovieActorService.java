package com.pmsj.cinema.business.service;

import com.pmsj.cinema.common.entity.MovieActor;
import com.pmsj.cinema.common.mapper.MovieActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/2 15:00
 */
@Service
public class MovieActorService {
    @Autowired
    MovieActorMapper movieActorMapper;

    public List<MovieActor> selectActor(Integer movieId){
        return movieActorMapper.selectActor(movieId);
    }

    public List<MovieActor> selectDirector(Integer movieId){
        return movieActorMapper.selectDirector(movieId);
    }
}
