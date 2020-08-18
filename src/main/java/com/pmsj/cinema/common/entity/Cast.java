package com.pmsj.cinema.common.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author EDZ
 */
@Data
@ToString
public class Cast {
    private String actorRole;

    private Integer actorId;

    private String actorName;

    private Integer actorTpye;

    private String actorImg;

    private List<Movie> movie;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName == null ? null : actorName.trim();
    }

    public Integer getActorTpye() {
        return actorTpye;
    }

    public void setActorTpye(Integer actorTpye) {
        this.actorTpye = actorTpye;
    }

    public String getActorImg() {
        return actorImg;
    }

    public void setActorImg(String actorImg) {
        this.actorImg = actorImg == null ? null : actorImg.trim();
    }
}