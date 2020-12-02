package com.erhuo.service;

import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String username,String password){
        User user = userMapper.login(username, password);
        if(user!=null) {
            userMapper.updateDate(user.getUserId(),new Date());
        }
        return user;
    }

    public String logon(String username,String password) {
        int num = userMapper.checkUserByUsername(username);
        if(num!=0) {
            return "error";
        }
        userMapper.logon(username,password);
        return "ok";
    }
}
