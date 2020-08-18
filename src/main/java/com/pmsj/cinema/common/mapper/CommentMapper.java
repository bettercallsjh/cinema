package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(
            @Param(value = "userId") Integer userId,
            @Param(value = "userSocre")  Integer userSocre,
            @Param(value = "commentCreateTime")     Date commentCreateTime,
            @Param(value = "movieId")   Integer movieId,
            @Param(value = "userComment")  String userComment,
            @Param(value = "likeCount")    Integer likeCount
               );

    Comment selectByPrimaryKey(Integer commentId);

    List<Comment> selectAllByMovie(Integer movieId);

    int updateLikeCount(@Param(value = "likeCount") Integer likeCount, @Param(value = "commentId") Integer commentId);

    Integer getHasComment(@Param("userId") Integer userId, @Param("movieId") Integer movieId);
}