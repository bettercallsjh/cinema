package com.pmsj.cinema.business.service;

import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.entity.MovieTpye;
import com.pmsj.cinema.common.mapper.MovieMapper;
import com.pmsj.cinema.common.mapper.MovieTpyeMapper;
import com.pmsj.cinema.common.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/1 11:20
 */
@Service
public class MovieDetailsService {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MovieTpyeMapper movieTpyeMapper;

    @Autowired
    RedisUtils redisUtils;

    public Movie selectByPrimaryKey(Integer movieId) {
        Movie hget = (Movie) redisUtils.hget("cinema-movie", "movie-" + movieId);

        if (hget != null) {
            return hget;
        }

        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        redisUtils.hset("cinema-movie", "movie-" + movieId, movie);

        return movieMapper.selectByPrimaryKey(movieId);


    }

    public List<MovieTpye> selectByMovieId(Integer movieId) {
        return movieTpyeMapper.selectByMovieId(movieId);
    }

    public int getMovieIsOnline(Integer movieId) {
        System.out.println(movieMapper.getMovieIsOnline(movieId));
        if (movieMapper.getMovieIsOnline(movieId) == null || movieMapper.getMovieIsOnline(movieId).size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
