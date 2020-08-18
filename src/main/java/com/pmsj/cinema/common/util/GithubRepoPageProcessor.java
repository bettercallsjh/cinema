package com.pmsj.cinema.common.util;

import com.pmsj.cinema.common.entity.Movie;
import com.pmsj.cinema.common.vo.MovieBugAddVo;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;


@Repository
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    static List<MovieBugAddVo> movies = new ArrayList<>();

    @Override
    public void process(Page page) {

        System.out.println(page.getHtml());

        List<String> all = page.getHtml().css(".movie-poster img").regex("<img\\b[^<>]*?\\bsrc[\\s\\t\\r\\n]*=[\\s\\t\\r\\n]*[\"\"']?[\\s\\t\\r\\n]*(?<imgUrl>[^\\s\\t\\r\\n\"\"'<>]*)[^<>]*?/?[\\s\\t\\r\\n]*>").regex("^http.*$").all();
        //name
        List<String> all1 = page.getHtml().css(".channel-detail a").regex(">.*<").all();
        System.out.println(page.getHtml().css(".figure img"));
        System.out.println(all);
        int i = 0;

//        <a[^>]*href[=\"\'\s]+([^\"\']*)[\"\']?[^>]*>

        List<String> paths = page.getHtml().css(".movie-item a").regex("<a[^>]*href[=\\\"\\'\\s]+([^\\\"\\']*)[\\\"\\']?[^>]*>").all();


        System.out.println(paths);
        for (String path : paths) {
            MovieBugAddVo movie = new MovieDetails().getMovie("https://maoyan.com" + path);

            System.out.println(movie);
            if (movie.getMovieName() != null && !movies.contains(movie)) {
                movies.add(movie);
            }
        }

        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


    public List<MovieBugAddVo> getPaChong() {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://maoyan.com/films?showType=2").thread(1).run();
        return movies;
    }

}
