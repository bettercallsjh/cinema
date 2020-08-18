package com.pmsj.cinema.common.util;

import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.entity.MovieTpye;
import com.pmsj.cinema.common.vo.MovieBugAddVo;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MovieDetails implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    static MovieBugAddVo movie = null;


    @Override
    public void process(Page page) {

        movie = new MovieBugAddVo();
        List<String> imgs = page.getHtml().css(".avatar").regex("<img\\b[^<>]*?\\bsrc[\\s\\t\\r\\n]*=[\\s\\t\\r\\n]*[\"\"']?[\\s\\t\\r\\n]*(?<imgUrl>[^\\s\\t\\r\\n\"\"'<>]*)[^<>]*?/?[\\s\\t\\r\\n]*>").regex("^http.*$").all();
        List<String> movieName = page.getHtml().css(".movie-brief-container").regex("<h1.*?>(.*?)<\\/h1>").all();
        List<String> movieEname = page.getHtml().css(".movie-brief-container .ename").regex("<div.*?>(.*?)<\\/div>").all();
        List<String> all = page.getHtml().css(".movie-brief-container ul li").all();
        List<String> movieinfo = page.getHtml().css(".tab-desc .dra").regex("<span.*?>(.*?)<\\/span>").all();

        List<String> types = page.getHtml().css(".movie-brief-container a.text-link").regex("<a.*?>(.*?)<\\/a>").all();

        //设置所有的影片类别
        List<MovieTpye> tpyeList = new ArrayList<>();
        for (String type : types) {
            MovieTpye movieTpye = new MovieTpye();
            movieTpye.setTypeName(type);
            tpyeList.add(movieTpye);
        }
        movie.setMovieTpyes(tpyeList);


//        .tab-desc .celebrity-container .celebrity-group .celebrity
        //导演图片
        List<String> daoYeImg = page.getHtml().css(".tab-desc .celebrity-container .celebrity-group:first-child").regex("<a.*?>(.*?)<\\/a>").regex("<img\\b[^<>]*?\\bsrc[\\s\\t\\r\\n]*=[\\s\\t\\r\\n]*[\"\"']?[\\s\\t\\r\\n]*(?<imgUrl>[^\\s\\t\\r\\n\"\"'<>]*)[^<>]*?/?[\\s\\t\\r\\n]*>").all();
        List<String> daoYeName = page.getHtml().css(".tab-desc .celebrity-container .celebrity-group:first-child .info a").regex("<a.*?>(.*?)<\\/a>").all();

        System.out.println("daoYeImg" + daoYeImg);
        System.out.println("daoYeName" + daoYeName);


        List<Cast> castList = new ArrayList<>();
        for (int i = 0; i < daoYeImg.size(); i++) {
            Cast cast = new Cast();
            cast.setActorTpye(0);/* 0 代表是导演*/
            cast.setActorImg(daoYeImg.get(i));
            cast.setActorName(daoYeName.get(i));
            castList.add(cast);
        }


        //演员图片
        List<String> yanYuanImg = page.getHtml().css(".tab-desc .celebrity-container .celebrity-group:last-child").regex("<a.*?>(.*?)<\\/a>").regex("<img\\b[^<>]*?\\bsrc[\\s\\t\\r\\n]*=[\\s\\t\\r\\n]*[\"\"']?[\\s\\t\\r\\n]*(?<imgUrl>[^\\s\\t\\r\\n\"\"'<>]*)[^<>]*?/?[\\s\\t\\r\\n]*>").all();
        List<String> yanYuanName = page.getHtml().css(".tab-desc .celebrity-container .celebrity-group:last-child .info a").regex("<a.*?>(.*?)<\\/a>").all();
        List<String> yanYuanRole = page.getHtml().css(".celebrity-container .celebrity .role").regex("<span.*?>(.*?)<\\/span>").all();


        for (int i = 0; i < yanYuanImg.size(); i++) {
            Cast cast = new Cast();
            cast.setActorTpye(1);
            cast.setActorImg(yanYuanImg.get(i));
            cast.setActorRole(yanYuanRole.get(i));
            cast.setActorName(yanYuanName.get(i));
            castList.add(cast);
        }

        movie.setCasts(castList);


        System.out.println("yanYuanImg" + yanYuanImg);
        System.out.println("yanYuanName" + yanYuanName);

        System.out.println(types);
        String area = all.get(1).replaceAll("<li class=\"ellipsis\">", "").replaceAll("</li>", "").split("/")[0];

        String time = all.get(1).replaceAll("<li class=\"ellipsis\">", "").replaceAll("</li>", "").split("/")[1];
        String date = all.get(2).replaceAll("<li class=\"ellipsis\">", "").replaceAll("</li>", "").substring(0, 10);

        movie.setMovieImg(imgs.get(0));
        movie.setMovieArea(area);
        movie.setMovieImg(imgs.get(0));
        movie.setMovieName(movieName.get(0));
        movie.setMovieEname(movieEname.get(0));
        movie.setMovieInfo(movieinfo.get(0));
        movie.setMovieTime(time);


        try {
            movie.setMovieReleaseTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //name
        int i = 0;

        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }

        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }


    public MovieBugAddVo getMovie(String path) {
        Spider.create(new MovieDetails()).addUrl(path).thread(1).run();

        return movie;
    }

}
