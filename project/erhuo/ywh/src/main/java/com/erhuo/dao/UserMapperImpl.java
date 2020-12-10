package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public User queryById(int userId) {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.queryById(userId);
        return user;
    }
}
