package com.pmsj.cinema.common.controller;

import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.mapper.MovieMapper;
import com.pmsj.cinema.common.service.MovieBugAddVoService;
import com.pmsj.cinema.common.util.GithubRepoPageProcessor;
import com.pmsj.cinema.common.vo.MovieBugAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class MovieData {


    @Autowired
    MovieBugAddVoService movieBugAddVoService;

    @RequestMapping("getMovieData")
    @ResponseBody
    public String getData() {
        List<MovieBugAddVo> paChong = new GithubRepoPageProcessor().getPaChong();
        System.out.println(paChong);

        for (MovieBugAddVo movie : paChong) {
            movieBugAddVoService.addMovieBugAddVo(movie);
        }
        return "abc";
    }

}
