package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.MovieActorService;
import com.pmsj.cinema.common.entity.MovieActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/2 14:59
 */
@RestController
@RequestMapping("movieActorOrDirector")
public class MovieActorController {
    @Autowired
    MovieActorService movieActorService;

    @RequestMapping("/Actor/{movieId}")
    public List<MovieActor> selectActor(@PathVariable Integer movieId){
        return movieActorService.selectActor(movieId);
    }
    @RequestMapping("/Director/{movieId}")
    public List<MovieActor> selectDirector(@PathVariable Integer movieId){
        return movieActorService.selectDirector(movieId);
    }
}
