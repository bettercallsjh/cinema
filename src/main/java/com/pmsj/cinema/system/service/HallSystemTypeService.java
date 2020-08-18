package com.pmsj.cinema.system.service;

import com.pmsj.cinema.common.entity.HallTpye;
import com.pmsj.cinema.common.mapper.HallTpyeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mhd
 * @className BrandService
 * @description TODO
 * @create 2020/7/1
 * @since 1.0.0
 */
@Service
public class HallSystemTypeService {
    @Autowired(required = false)
    HallTpyeMapper hallTpyeMapper;

    public List<HallTpye> getAllHallType(){
        return hallTpyeMapper.selectAll();
    }

}
