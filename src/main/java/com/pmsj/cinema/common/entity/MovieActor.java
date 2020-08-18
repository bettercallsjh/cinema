package com.pmsj.cinema.common.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MovieActor {
    private Integer movieId;

    private Integer actorId;

    private String movieActorRole;

    private Integer movieActorType;

    private String actorName;

    private String actorImg;


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getMovieActorRole() {
        return movieActorRole;
    }

    public void setMovieActorRole(String movieActorRole) {
        this.movieActorRole = movieActorRole == null ? null : movieActorRole.trim();
    }

    public Integer getMovieActorType() {
        return movieActorType;
    }

    public void setMovieActorType(Integer movieActorType) {
        this.movieActorType = movieActorType;
    }
}