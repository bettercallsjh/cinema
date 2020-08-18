package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.MovieTopService;
import com.pmsj.cinema.common.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/6 14:40
 */
@RestController
@RequestMapping("MovieTop")
public class MovieTopController {
    @Autowired
    MovieTopService movieTopService;

    @RequestMapping("/top")
    public List<Movie> selectTopTen(Integer status) {

        return movieTopService.selectTopTen(status);
    }
}
