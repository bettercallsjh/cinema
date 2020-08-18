package com.pmsj.cinema.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.common.entity.MovieActor;
import com.pmsj.cinema.common.entity.MovieTpye;
import com.pmsj.cinema.common.mapper.CastMapper;
import com.pmsj.cinema.common.mapper.MovieActorMapper;
import com.pmsj.cinema.common.vo.AutocompleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/7 13:54
 **/
@Service
public class CastService {
    @Autowired
    CastMapper castMapper;
    @Autowired
    MovieActorMapper movieActorMapper;


    public AutocompleteVo<Cast> getAllCast() {
        List<Cast> castList = castMapper.selectAll();

        AutocompleteVo<Cast> castAutocompleteVo = new AutocompleteVo<>();
        castAutocompleteVo.setData(castList);
        castAutocompleteVo.setCode(200);
        castAutocompleteVo.setMsg("zzzz");
        return castAutocompleteVo;
    }

    public List<MovieActor> getCast0AllByMovieId(Integer movieId) {
//        castMapper.getCast0AllByMovieId(movieId);

        return movieActorMapper.getCast0AllByMovieId(movieId);

    }

    public List<MovieActor> getCast1AllByMovieId(Integer movieId) {
        return movieActorMapper.getCast1AllByMovieId(movieId);
    }

    public PageInfo<Cast> getAllCastByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Cast> castList = castMapper.selectAll();
        return new PageInfo<Cast>(castList);
    }

    public void add(Cast cast) {
    }
}
