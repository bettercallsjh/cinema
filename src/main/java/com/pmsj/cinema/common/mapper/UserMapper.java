package com.pmsj.cinema.common.mapper;

import com.pmsj.cinema.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    Double getUserAllMoney(int i);

    long getUserAllOrderCount(int i);

    User selectUser(@Param("userAccount") String account);

    User selectPhone(@Param("userPhone") String phone);

    User selectEmail(@Param("userEmail") String email);

    void register(@Param("userAccount") String userAccount, @Param("userPassword") String password, @Param("userEmail") String email, @Param("userPhone") String phone, @Param("userPhoto") String photo);

    User accountLogin(@Param("userAccount") String account, @Param("userPassword") String password);

    User emailLogin(@Param("userEmail") String email);

    int updatePass(@Param("userEmail") String email, @Param("userPassword") String password);

    Integer updateUser(User user);

    int updatePhoto(User user);
}