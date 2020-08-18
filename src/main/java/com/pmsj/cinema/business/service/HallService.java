package com.pmsj.cinema.business.service;

import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.common.entity.Hall;
import com.pmsj.cinema.common.mapper.HallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mhd
 * @className HallMovieService
 * @description TODO
 * @create 2020/7/7
 * @since 1.0.0
 */
@Service
public class HallService {
    @Autowired(required = false)
    HallMapper hallMapper;

    public Hall selectById(Integer id) {
        if (id==null){
            throw new NullParametersException("HallId is null");
        }
        return hallMapper.selectByPrimaryKey(id);
    }
}
