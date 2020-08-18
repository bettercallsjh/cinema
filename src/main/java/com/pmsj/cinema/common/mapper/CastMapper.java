package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Cast;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CastMapper {
    int deleteByPrimaryKey(Integer actorId);

    int insert(Cast record);

    Cast selectByPrimaryKey(Integer actorId);

    List<Cast> selectAll();

    int updateByPrimaryKey(Cast record);

    Cast selectByCastName(String actorName);

    int getMaxId();

}