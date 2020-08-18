package com.pmsj.cinema.system.controller;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/3 19:06
 **/

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.business.service.HallMovieService;
import com.pmsj.cinema.common.entity.*;
import com.pmsj.cinema.common.mapper.CinemaMapper;
import com.pmsj.cinema.common.mapper.HallMovieMapper;
import com.pmsj.cinema.common.vo.AutocompleteVo;
import com.pmsj.cinema.common.vo.CinemaVo;
import com.pmsj.cinema.common.vo.HallMovieVo;
import com.pmsj.cinema.system.service.CinemaService;
import com.pmsj.cinema.system.service.MovieService;
import com.pmsj.cinema.system.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cinema")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @Autowired
    MovieService movieService;

    @Autowired
    HallMovieService hallMovieService;

    @RequestMapping("getAllCinameType")
    @ResponseBody
    public List<Brand> getAllCinameType() {
        return cinemaService.getAllCinameType();
    }


    @RequestMapping("addCinema")
    @ResponseBody
    public Result add(Cinema cinema) {
        int i = 0;

        if (cinema.getCinemaLat() == null || cinema.getCinemaLng() == null || cinema.getCinemaLat() == "" || cinema.getCinemaLng() == "") {
            return new Result().error("地址不存在，请选择正确的地址", null);
        }
        int countCinemaByLngAndLat = cinemaService.getCountCinemaByLngAndLat(cinema.getCinemaLat(), cinema.getCinemaLng());
        if (countCinemaByLngAndLat > 0) {
            return new Result().error("当前影院地址已经存在", null);
        }


        try {
            cinemaService.addCinema(cinema);
        } catch (Exception e) {
            return new Result().error("添加失败", null);
        }
        return new Result().success("添加成功", null);
    }

    @RequestMapping("getAllCinema")
    @ResponseBody
    public ReturnUtil getAllCinema(Integer page, Integer limit, Cinema cinema) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 5;
        }

        PageInfo<CinemaVo> allCinema = cinemaService.getAllCinema(page, limit, cinema);
        ReturnUtil returnUtil = new ReturnUtil(0, "success", allCinema.getTotal(), allCinema.getList());
        return returnUtil;
    }


    @RequestMapping("getCinemaById")
    public String getCinemaById(Integer cinemaId, Map map) {
        Cinema cinema = cinemaService.getCinemaById(cinemaId);
        map.put("cinema", cinema);
        return "system/page/table/editCinema";

    }

    @RequestMapping("getAllHallType")
    @ResponseBody
    public List<HallTpye> getAllHallType() {
        return cinemaService.getAllHallType();
    }


    @RequestMapping("getAllCinemaByAll")
    @ResponseBody
    public PageInfo<CinemaVo> getAllCinemaByAll(String brand, String hallType, String area, String province, String city, Integer page) {

        if (page == null || page == 0) {
            page = 1;
        }
        return cinemaService.getAllCinemaByAll(brand, hallType, area, province, city, page);

    }


    @RequestMapping("getAllCinemaByMovie")
    @ResponseBody
    public PageInfo<CinemaVo> getAllCinemaByMovie(String brand, String hallType, String area, String province, Integer movieId, String city, Integer page) {

        if (page == null || page == 0) {
            page = 1;
        }

        return cinemaService.getAllCinemaByAll(brand, hallType, area, province, city, page, movieId);

    }

    @RequestMapping("editCinema")
    @ResponseBody
    public Result editCinema(Cinema cinema) {
        try {
            cinemaService.editCinema(cinema);
        } catch (Exception e) {
            return new Result().error("修改失败", null);
        }
        return new Result().success("修改成功", null);
    }


    @RequestMapping("delCinema")
    @ResponseBody
    public Result delCinema(Integer cinemaId) {

        try {
            cinemaService.delCinemaById(cinemaId);
        } catch (Exception e) {
            return new Result().error("删除失败", null);
        }
        return new Result().success("删除成功", null);

    }

    @RequestMapping("cinemaDetails")
    @ResponseBody
    public Cinema cinemaDetails(Integer cinemaId) {
        return cinemaService.getCinemaById(cinemaId);
    }

    @RequestMapping("getHallMovies")
    @ResponseBody
    public List<HallMovieVo> getHallMovies(Integer cinemaId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Integer movieId) throws ParseException {

        return cinemaService.getHallMovies(cinemaId, date, movieId);
    }


    @RequestMapping("getRecentTime")
    @ResponseBody
    public String getRecentTime(Integer cinemaId, Integer movieId) {
        Date recentTime = cinemaService.getRecentTime(cinemaId, movieId);
        return new SimpleDateFormat("yyyy-MM-dd").format(recentTime);
    }


    @RequestMapping("sortMoviePageByMoiveId")
    public String sortMoviePageByMoiveId(Integer cinemaId, Map map) {

        Cinema cinema = cinemaService.getCinemaById(cinemaId);
        List<Hall> hallList = cinemaService.getAllHallByCinemaId(cinemaId);
        map.put("cinema", cinema);
        map.put("hallList", hallList);


        return "system/page/table/sortMovie";

    }

    @RequestMapping("/movieName")
    @ResponseBody
    public AutocompleteVo getMovieByMovieName(String movieName) {
        List<Movie> movieByMovieName = movieService.getMovieByMovieName(movieName);

        AutocompleteVo autocompleteVo = new AutocompleteVo();
        autocompleteVo.setData(movieByMovieName);
        autocompleteVo.setCode(200);
        autocompleteVo.setMsg("");
        return autocompleteVo;

    }

    @RequestMapping("addHallMovie")
    @ResponseBody
    public String addHallMovie(HallMovie hallMovie, Integer time) {
        System.out.println(hallMovie);

        System.out.println(time);
        if (hallMovie.getHallId() == null) {
            return "请选择影厅";
        }
        if (hallMovie.getMovieId() == null) {
            return "没有这样的影片";
        }

        if (hallMovie.getStartTime().getTime() < new Date().getTime()) {
            return "影片开始时间已过";
        }


        Long dateTime = 1000L * time * 60 + hallMovie.getStartTime().getTime();
        hallMovie.setEndTime(new Date(dateTime));


        int add = hallMovieService.add(hallMovie);
        if (add != 0) {
            return "影厅该时间端被占用";
        }
        return "200";
    }


}


