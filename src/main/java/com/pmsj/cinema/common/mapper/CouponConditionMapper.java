package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.CouponCondition;
import java.util.List;

public interface CouponConditionMapper {
    int insert(CouponCondition record);

    List<CouponCondition> selectAll();
}