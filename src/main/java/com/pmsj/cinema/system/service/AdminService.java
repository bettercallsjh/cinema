package com.pmsj.cinema.system.service;

import com.pmsj.cinema.common.entity.Admin;
import com.pmsj.cinema.common.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangshuai
 * @date 2020/7/1 0001 10:22
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin selectAdmin(String username,String password) {
        return adminMapper.selectAdmin(username,password);
    }
}
