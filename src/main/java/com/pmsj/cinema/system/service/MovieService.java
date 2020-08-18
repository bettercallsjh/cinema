package com.pmsj.cinema.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.entity.MovieActor;
import com.pmsj.cinema.common.mapper.MovieActorMapper;
import com.pmsj.cinema.common.mapper.MovieMapper;
import com.pmsj.cinema.common.mapper.MovieTpyeMapper;
import com.pmsj.cinema.common.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/6 19:12
 **/
@Service
public class MovieService {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieTpyeMapper movieTpyeMapper;

    @Autowired
    MovieActorMapper movieActorMapper;

    @Autowired
    RedisUtils redisUtils;


    public PageInfo<Movie> getAllMovies(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Movie> movies = movieMapper.selectAll();
        return new PageInfo<Movie>(movies);

    }

    public List<Movie> getAllMovies() {
        return movieMapper.selectAll();
    }


    @Transactional
    public void addMovie(Movie movie, String[] movieTypes, String[] cast0, String[] cast1, String[] cast1Role) {
        movie.setMovieStatus(2);
        movieMapper.insert(movie);
        int movieMapperMaxId = movieMapper.getMaxId();
        movie.setMovieId(movieMapperMaxId);
        redisUtils.hset("cinema-movie", "movie-" + movieMapperMaxId, movie);

        //类别关联添加
        for (String movieType : movieTypes) {
            movieTpyeMapper.addMovieTypeMid(movieMapperMaxId, Integer.parseInt(movieType));
        }

        //导演的关联
        for (String s : cast0) {
            MovieActor movieActor = new MovieActor();
            movieActor.setActorId(Integer.parseInt(s));
            movieActor.setMovieId(movieMapperMaxId);
            movieActor.setMovieActorType(0);
            movieActorMapper.insert(movieActor);
        }

        //演员的关联
        for (int i = 0; i < cast1.length; i++) {
            MovieActor movieActor = new MovieActor();
            movieActor.setActorId(Integer.parseInt(cast1[i]));
            movieActor.setMovieId(movieMapperMaxId);
            movieActor.setMovieActorType(1);
            movieActor.setMovieActorRole("饰：" + cast1Role[i].replaceAll("饰：", ""));
            movieActorMapper.insert(movieActor);
        }
    }

    public int delMovie(Integer moiveId) {
        redisUtils.hdel("cinema-movie", "movie-" + moiveId);
        return movieMapper.deleteByPrimaryKey(moiveId);
    }

    public Movie getMoiveById(Integer movieId) {
        return movieMapper.selectByPrimaryKey(movieId);
    }

    public void updateByid(Movie movie, String[] movieTypes, String[] cast0, String[] cast1, String[] cast1Role) {

        movieMapper.updateByPrimaryKey(movie);

        Integer movieMapperMaxId = movie.getMovieId();

        movie.setMovieId(movieMapperMaxId);
        redisUtils.hset("cinema-movie", "movie-" + movieMapperMaxId, movie);

        //类别关联
        movieTpyeMapper.deleteByMovie(movieMapperMaxId);
        //类别关联添加
        for (String movieType : movieTypes) {
            movieTpyeMapper.addMovieTypeMid(movieMapperMaxId, Integer.parseInt(movieType));
        }


        movieActorMapper.delByMovie(movieMapperMaxId);

        //导演的关联
        for (String s : cast0) {
            MovieActor movieActor = new MovieActor();
            System.out.println(s);
            movieActor.setActorId(Integer.parseInt(s));
            movieActor.setMovieId(movieMapperMaxId);
            movieActor.setMovieActorType(0);
            movieActorMapper.insert(movieActor);
        }


        //演员的关联
        for (int i = 0; i < cast1.length; i++) {
            MovieActor movieActor = new MovieActor();
            movieActor.setActorId(Integer.parseInt(cast1[i]));
            movieActor.setMovieId(movieMapperMaxId);
            movieActor.setMovieActorType(1);
            movieActor.setMovieActorRole("饰：" + cast1Role[i].replaceAll("饰：", ""));
            movieActorMapper.insert(movieActor);
        }


    }

    public List<Movie> getMovieByMovieName(String movieName) {
        return movieMapper.getMovieByMovieName(movieName);
    }
}
