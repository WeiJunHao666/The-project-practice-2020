package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select username,nickname,img from user where user_id = #{userId}")
    public User queryById(int userId);
}
