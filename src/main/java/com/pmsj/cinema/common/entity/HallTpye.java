package com.pmsj.cinema.common.entity;

public class HallTpye {
    private Integer htId;

    private String htName;

    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    public String getHtName() {
        return htName;
    }

    public void setHtName(String htName) {
        this.htName = htName == null ? null : htName.trim();
    }
}