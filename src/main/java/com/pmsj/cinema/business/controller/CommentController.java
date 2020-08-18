package com.pmsj.cinema.business.controller;

import com.pmsj.cinema.business.service.CommentService;
import com.pmsj.cinema.common.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author sjh
 * @creat 2020/7/8 19:30
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/addComment")
    public int insert(Integer userId, Integer userSocre,
                      Date commentCreateTime, Integer movieId,
                      String userComment, Integer likeCount) {
        return commentService.insert(userId,userSocre,commentCreateTime,movieId,userComment,likeCount);
    }

    @RequestMapping("/like")
    public int updateLikeCount(Integer likeCount, Integer commentId) {
        System.out.println(likeCount);
        System.out.println(commentId);
        return commentService.updateLikeCount(likeCount, commentId);
    }

    @RequestMapping("/getComment")
    public List<Comment> selectAllByMovie(Integer movieId) {
        System.out.println(movieId);
        return commentService.selectAllByMovie(movieId);
    }
}
