package com.pmsj.cinema.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class Comment {
    private Integer commentId;

    private Integer userId;

    private Integer userSocre;
    @JsonFormat(pattern = "MM-dd")
    private Date commentCreateTime;

    private Integer movieId;

    private String userComment;

    private Integer likeCount;

    private String userName;

    private String userPhoto;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment == null ? null : userComment.trim();
    }
}