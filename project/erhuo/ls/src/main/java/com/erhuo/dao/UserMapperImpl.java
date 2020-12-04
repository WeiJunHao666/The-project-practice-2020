package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.Date;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    public User queryUserById(int userId) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.queryUserById(userId);
        return user;
    }

    @Override
    public User login(String username, String password) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.login(username,password);
        return user;
    }

    @Override
    public void updateDate(int id, Date date) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        userMapper.updateDate(id,date);
    }

    @Override
    public void logon(String username, String password) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        userMapper.logon(username,password);
    }

    @Override
    public int checkUserByUsername(String username) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        int num = userMapper.checkUserByUsername(username);
        return num;
    }

    @Override
    public void updateUser(User user) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        System.out.println(user);
        userMapper.updateUser(user);
    }

}
