package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where user_id = #{userId}")
    public User queryUserById(int userId);
}
