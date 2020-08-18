package com.pmsj.cinema.system.controller;

import com.github.pagehelper.PageInfo;
import com.pmsj.cinema.business.service.MovieAllService;
import com.pmsj.cinema.common.entity.*;
import com.pmsj.cinema.common.mapper.MovieActorMapper;
import com.pmsj.cinema.common.vo.AutocompleteVo;
import com.pmsj.cinema.system.service.CastService;
import com.pmsj.cinema.system.service.CinemaService;
import com.pmsj.cinema.system.service.MovieService;
import com.pmsj.cinema.system.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/6 19:10
 **/
@Controller
@RequestMapping("movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    CastService castService;

    @Autowired
    MovieAllService movieAllService;

    @Autowired
    MovieActorMapper movieActorMapper;

    @Autowired
    CinemaService cinemaService;


    @RequestMapping("getAllMovie")
    @ResponseBody
    public ReturnUtil getAllMovie(Integer page, Integer limit) {


        if (page == null || page == 0) {
            page = 1;
        }
        System.out.println(limit);


        PageInfo<Movie> allMovies = movieService.getAllMovies(page, limit);

        ReturnUtil returnUtil = new ReturnUtil(0, "success", allMovies.getTotal(), allMovies.getList());
        return returnUtil;
    }

    @RequestMapping("addMovie")
    @ResponseBody
    public Result addMovie(HttpServletRequest request, Movie movie, String[] movieTypes, String[] cast0, String[] cast1, String[] cast1Role, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        System.out.println(movie);

        movie.setMovieTime(movie.getMovieTime().replaceAll("分钟", "") + "分钟");

        System.out.println(image);

        //判断用户是否选择图片
        if (image == null || image.getOriginalFilename().length() <= 0) {
        } else {
//        image
            String fileName = image.getOriginalFilename();
            String substring = "";
            if (fileName.lastIndexOf(".") != -1) {
                substring = fileName.substring(fileName.lastIndexOf("."));
            }
            String name = UUID.randomUUID().toString().replaceAll("-", "") + substring;
            request.getServletPath();

            String property = System.getProperty("user.dir");
            String realPath = property + "\\src\\main\\resources\\static\\img";

            movie.setMovieImg("/img/" + name);
            System.out.println(realPath);
//            image.getInputStream. (realPath + "/" + name);
//            goods.setImage("/images/goods/" + name);
            try {
                File fileMkdir = new File(realPath);

                if (!fileMkdir.exists()) {
                    fileMkdir.mkdir();
                }
                FileOutputStream os = new FileOutputStream(fileMkdir.getPath() + "\\" + name);
                InputStream in = image.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) { //读取文件
                    os.write(b);
                }
                os.flush(); //关闭流
                in.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                ;
                System.out.println("上传失败！");
            }
        }

        movieService.addMovie(movie, movieTypes, cast0, cast1, cast1Role);

        return new Result().success("添加成功", null);
    }


    @RequestMapping("delMovie")
    @ResponseBody
    public String delMovie(Integer moiveId) {
        int i = movieService.delMovie(moiveId);
        if (i == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


    @RequestMapping("editMovie")
    public String editMovie(Integer movieId, Map map) {
        //
        Movie movie = movieService.getMoiveById(movieId);
        map.put("movie", movie);

        /*获取未被选择类型*/
        List<MovieTpye> movieTpyes = movieAllService.selectNoMovieType(movieId);
        map.put("movieTpyes", movieTpyes);

        /*获取选择类型*/
        List<MovieTpye> movieCheckedTpyes = movieAllService.selectCheckedTpyes(movieId);
        map.put("movieCheckedTpyes", movieCheckedTpyes);

        /*获取导演和演员*/
        List<MovieActor> cast0s = castService.getCast0AllByMovieId(movieId);
        map.put("cast0s", cast0s);
        List<MovieActor> cast1s = castService.getCast1AllByMovieId(movieId);
        map.put("cast1s", cast1s);

        //获取所有的演员
//        List<MovieActor> movieActors = movieActorMapper.selectAll();
        List<Cast> allCast = castService.getAllCast().getData();
        map.put("allCast", allCast);


        return "system/page/table/editMovie";
    }

    @RequestMapping("editMovieById")
    @ResponseBody
    public String editMovieById(HttpServletRequest request, Movie movie, String[] movieTypes, String[] cast0, String[] cast1, String[] cast1Role, @RequestParam(value = "image", required = false) MultipartFile image, String oldImg) {

        System.out.println(movie);

        movie.setMovieTime(movie.getMovieTime().replaceAll("分钟", "") + "分钟");
        System.out.println("fghj:" + Arrays.toString(cast0));
        System.out.println(Arrays.toString(cast1));
        System.out.println(Arrays.toString(cast1Role));

        System.out.println(image);

        //判断用户是否选择图片
        if (image == null) {
        } else {
//        image
            String fileName = image.getOriginalFilename();
            String substring = "";
            if (fileName.lastIndexOf(".") != -1) {
                substring = fileName.substring(fileName.lastIndexOf("."));
            }
            String name = UUID.randomUUID().toString().replaceAll("-", "") + substring;
            request.getServletPath();

            String property = System.getProperty("user.dir");
            String realPath = property + "\\src\\main\\resources\\static\\img";

            movie.setMovieImg("/img/" + name);
            System.out.println(realPath);
//            image.getInputStream. (realPath + "/" + name);
//            goods.setImage("/images/goods/" + name);
            try {
                File fileMkdir = new File(realPath);

                if (!fileMkdir.exists()) {
                    fileMkdir.mkdir();
                }
                FileOutputStream os = new FileOutputStream(fileMkdir.getPath() + "\\" + name);
                InputStream in = image.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) { //读取文件
                    os.write(b);
                }
                os.flush(); //关闭流
                in.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                ;
                System.out.println("上传失败！");
            }
        }

        movieService.updateByid(movie, movieTypes, cast0, cast1, cast1Role);
        return "修改成功";

    }


}
