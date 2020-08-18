package com.pmsj.cinema.common.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MovieTpye {
    private Integer movieId;

    private Integer typeId;

    private String typeName;

    private Integer tpyeStatus;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getTpyeStatus() {
        return tpyeStatus;
    }

    public void setTpyeStatus(Integer tpyeStatus) {
        this.tpyeStatus = tpyeStatus;
    }
}