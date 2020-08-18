package com.pmsj.cinema.business.service;

import com.pmsj.cinema.common.entity.User;
import com.pmsj.cinema.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/1 11:08
 **/

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Double getUserAllMoney(int i) {
        return userMapper.getUserAllMoney(i);
    }


    public int getUserAllOrderCount(int i) {
        long a = userMapper.getUserAllOrderCount(i);

        return (int) a;
    }

    public User selectUser(String account) {
        return userMapper.selectUser(account);
    }

    public User selectPhone(String phone) {
        return userMapper.selectPhone(phone);
    }

    public User selectEmail(String email) {
        return userMapper.selectEmail(email);
    }

    public void register(String userAccount, String password, String email, String phone, String photo) {
        userMapper.register(userAccount, password, email, phone, photo);
    }

    public User accountLogin(String account, String password) {
        return userMapper.accountLogin(account, password);
    }

    public User emailLogin(String email) {
        return userMapper.emailLogin(email);
    }

    public Integer updatePass(String email, String password) {
        return userMapper.updatePass(email, password);
    }

    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int updatePhoto(User user) {
        return userMapper.updatePhoto(user);
    }
}
