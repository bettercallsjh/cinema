package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Condition;
import java.util.List;

public interface ConditionMapper {
    int deleteByPrimaryKey(Integer conditionId);

    int insert(Condition record);

    Condition selectByPrimaryKey(Integer conditionId);

    List<Condition> selectAll();

    int updateByPrimaryKey(Condition record);
}