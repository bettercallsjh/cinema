package com.pmsj.cinema.common.vo;

import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.entity.MovieTpye;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/2 10:36
 **/
@Data
@ToString
public class MovieBugAddVo {

    private Integer movieId;

    private String movieName;

    private Date movieReleaseTime;

    private String movieArea;

    private String movieInfo;

    private String movieImg;

    private String movieEname;

    private Float movieScore;

    private Integer movieStatus;

    private Integer movieCount;

    private Integer movieBoxOffice;

    private String movieTime;

    private List<MovieTpye> movieTpyes;

    private List<Cast> casts;


//    private

    private List<Cast> cast;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieBugAddVo movieBugAddVo = (MovieBugAddVo) o;
        return Objects.equals(movieName, movieBugAddVo.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName);
    }


}
