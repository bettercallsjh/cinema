package com.pmsj.cinema.business.service;

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/1 10:48
 *
 */
@Service
public class MovieIndexService {
    @Autowired
    MovieMapper movieMapper;

    public List<Movie> selectOnline(){
        return movieMapper.selectOnline();
    }

    public List<Movie> selectComingSoon(){
        return movieMapper.selectComingSoon();
    }

    public long onlineTotal(){
        List<Movie> onlineList = movieMapper.selectOnline();
        PageInfo<Movie> onlineNumber = new PageInfo<>(onlineList);
        long online = onlineNumber.getTotal();
        return online;
    }
    public long comingSoonTotal(){
        List<Movie> comingSoonList = movieMapper.selectComingSoon();
        PageInfo<Movie> comingSoonNumber = new PageInfo<>(comingSoonList);
        long comingSoon = comingSoonNumber.getTotal();
        return comingSoon;
    }
    public List<Movie> selectIndexTopMovie(Integer status){
        return movieMapper.selectIndexTopMovie(status);
    }
}
