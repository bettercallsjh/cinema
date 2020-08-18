package com.pmsj.cinema.business.controller;

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.business.service.MovieAllService;
import com.pmsj.cinema.common.entity.Movie;
/*import com.pmsj.cinema.common.entity.MovieRepository;*/
import com.pmsj.cinema.common.entity.MovieTpye;
import com.pmsj.cinema.system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/4 11:11
 */
@RestController
@RequestMapping("AllMovie")
public class MovieAllController {
    @Autowired
    MovieAllService movieAllService;

    @Autowired
    MovieService movieService;

/*

    @Autowired
    MovieRepository movieRepository;
    @PostConstruct
    public void init() {
        List<Movie> allMovies = movieService.getAllMovies();
        movieRepository.saveAll(allMovies);
    }
*/

    @RequestMapping("/Movie")
    public PageInfo<Movie> selectAllMovie(Integer offset, Integer limit, @RequestParam(value = "status") Integer movieStatus, @RequestParam(value = "tId") Integer typeId, @RequestParam(value = "area") String movieArea, @RequestParam(value = "year") String movieReleaseTime, @RequestParam Integer paixu) {
        System.out.println(paixu);
        return movieAllService.selectAllMovie(offset, limit, movieStatus, typeId, movieArea, movieReleaseTime, paixu);
    }

    @RequestMapping("/AllMovieType")
    public List<MovieTpye> selectAllMovieType() {
        return movieAllService.selectAllMovieType();
    }

    @RequestMapping("/MovieByKeyWord")
    public List<Movie> getMovieByKeyWord(String keyWord) {
        return movieAllService.getMovieByKeyWord(keyWord);
    }

}
