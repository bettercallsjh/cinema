package com.pmsj.cinema.business.service;

import com.github.pagehelper.PageHelper;
import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/6 14:39
 */
@Service
public class MovieTopService {
    @Autowired
    MovieMapper movieMapper;

    public List<Movie> selectTopTen(Integer status) {

//        PageHelper.startPage(1, 10);
        return movieMapper.selectTopTen(status);
    }
}
