package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public User queryById(int userId) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.queryById(userId);
        return user;
    }

    @Override
    public List<User> queryUsersByName(List<String> usernames) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        List<User> userList= userMapper.queryUsersByName(usernames);
        return userList;
    }

    @Override
    public String queryImgByName(String username) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        String img = userMapper.queryImgByName(username);

        return img;
    }
}
