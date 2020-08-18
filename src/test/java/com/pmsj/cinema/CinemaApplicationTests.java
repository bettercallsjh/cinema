package com.pmsj.cinema;

import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.mapper.MovieMapper;
import com.pmsj.cinema.common.util.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CinemaApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MovieMapper movieMapper;

    @Test
    void contextLoads() {
        Movie movie = movieMapper.selectByPrimaryKey(190);
        redisUtils.hset("cinema", "movie1", movie);

        Movie hget = (Movie) redisUtils.hget("cinema", "movie1");

        System.out.println(hget);
    }

}
