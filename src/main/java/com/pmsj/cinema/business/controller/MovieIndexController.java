package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.MovieIndexService;
import com.pmsj.cinema.common.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author sjh
 * @creat 2020/7/1 10:45
 *
 */
@RestController
@RequestMapping("index")
public class MovieIndexController {
    @Autowired
    MovieIndexService movieIndexService;
    @RequestMapping("/online")
    public  List<Movie> selectOnline(){
        List<Movie> movieOnline = movieIndexService.selectOnline();
        return movieOnline;
    }
    @RequestMapping("/comingSoon")
    public List<Movie> selectComingSoon(){
        List<Movie> movieComingSoon = movieIndexService.selectComingSoon();
        return movieComingSoon;
    }
    @RequestMapping("/onlineTotal")
    public long onlineTotal(){
        return movieIndexService.onlineTotal();
    }
    @RequestMapping("/comingSoonTotal")
    public long comingSoonTotal(){
        return movieIndexService.comingSoonTotal();
    }
    @RequestMapping("/TopMovie")
    public List<Movie> selectIndexTopMovie(Integer status){
        return movieIndexService.selectIndexTopMovie(status);
    }

}
