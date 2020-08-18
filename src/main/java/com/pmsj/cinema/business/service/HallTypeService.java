package com.pmsj.cinema.business.service;

import com.pmsj.cinema.business.exception.NullParametersException;
import com.pmsj.cinema.common.entity.HallTpye;
import com.pmsj.cinema.common.mapper.HallTpyeMapper;
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
public class HallTypeService {
    @Autowired(required = false)
    HallTpyeMapper hallTpyeMapper;

    public HallTpye selectById(Integer id) {
        if (id==null){
            throw new NullParametersException("HallTypeId is null");
        }
        return hallTpyeMapper.selectByPrimaryKey(id);
    }
}
