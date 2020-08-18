package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.HallTpye;
import java.util.List;

public interface HallTpyeMapper {
    int deleteByPrimaryKey(Integer htId);

    int insert(HallTpye record);

    HallTpye selectByPrimaryKey(Integer htId);

    List<HallTpye> selectAll();

    int updateByPrimaryKey(HallTpye record);
}