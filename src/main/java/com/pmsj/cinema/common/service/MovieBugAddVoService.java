package com.pmsj.cinema.common.service;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/2 11:06
 **/

import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.common.entity.MovieActor;
import com.pmsj.cinema.common.entity.MovieTpye;
import com.pmsj.cinema.common.mapper.CastMapper;
import com.pmsj.cinema.common.mapper.MovieActorMapper;
import com.pmsj.cinema.common.mapper.MovieMapper;
import com.pmsj.cinema.common.mapper.MovieTpyeMapper;
import com.pmsj.cinema.common.vo.MovieBugAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieBugAddVoService {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    CastMapper castMapper;

    @Autowired
    MovieActorMapper movieActorMapper;

    @Autowired
    MovieTpyeMapper movieTpyeMapper;

    public void addMovieBugAddVo(MovieBugAddVo movieBugAddVo) {
        /*影片的添加*/
        movieMapper.insertByMovieBugAddVo(movieBugAddVo);

        int movieMax = movieMapper.getMaxId();


        //类别的添加
        List<MovieTpye> movieTpyes = movieBugAddVo.getMovieTpyes();

        for (MovieTpye movieTpye : movieTpyes) {

            MovieTpye movieTpye1 = movieTpyeMapper.selectByName(movieTpye.getTypeName().trim());


            if (movieTpye1 == null) {
                movieTpyeMapper.insert(movieTpye);
                int maxId = movieTpyeMapper.getMaxId();

                //添加类别的关联
                movieTpyeMapper.addMovieTypeMid(movieMax, maxId);
            } else {


                //添加类别的关联
                movieTpyeMapper.addMovieTypeMid(movieMax, movieTpye1.getTypeId());
            }
        }


        //导演演员的添加
        List<Cast> casts = movieBugAddVo.getCasts();

        for (Cast cast : casts) {
            Cast cast1 = castMapper.selectByCastName(cast.getActorName().trim());
            MovieActor movieActor = new MovieActor();
            if (cast1 == null) {
                castMapper.insert(cast);
                int maxId = castMapper.getMaxId();
                //关联导演或者演员
                movieActor.setActorId(maxId);
            } else {
                movieActor.setActorId(cast1.getActorId());
            }

            movieActor.setMovieId(movieMax);
            movieActor.setMovieActorType(cast.getActorTpye());
            movieActor.setMovieActorRole(cast.getActorRole());
            movieActorMapper.insert(movieActor);
        }


    }

}
