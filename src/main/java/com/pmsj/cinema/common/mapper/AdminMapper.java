package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    Admin selectAdmin(@Param("adminAccount") String username, @Param("adminPassword") String password);
}