package com.erhuo.service;

import com.alibaba.fastjson.JSON;
import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String login(String username,String password){
        User user = userMapper.login(username, password);
        if(user!=null) {
            userMapper.updateDate(user.getUserId(),new Date());
        }
        if(user==null) {
            int num = userMapper.checkUserByUsername(username);
            if(num==0) {
                return "usernameError";
            } else {
                return "passwordError";
            }
        }
        return JSON.toJSONString(user);
    }

    public String logon(String username,String password) {
        int num = userMapper.checkUserByUsername(username);
        if(num!=0) {
            return "error";
        }
        userMapper.logon(username,password);
        return "ok";
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
