package com.pmsj.cinema.system.controller;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/7 13:47
 **/

import com.pmsj.cinema.common.entity.Cast;
import com.pmsj.cinema.common.vo.AutocompleteVo;
import com.pmsj.cinema.system.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Cast")
public class CastController {

    @Autowired
    CastService castService;


    @RequestMapping("getAllCast")
    @ResponseBody
    public AutocompleteVo<Cast> getAllCast() {
        return castService.getAllCast();
    }
}
