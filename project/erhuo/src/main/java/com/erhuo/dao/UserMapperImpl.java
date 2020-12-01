package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    public User queryUserById(int userId) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.queryUserById(userId);
        return user;
    }
}
