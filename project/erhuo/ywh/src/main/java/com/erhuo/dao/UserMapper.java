package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select username,nickname,img from user where user_id = #{userId}")
    public User queryById(int userId);

    public List<User> queryUsersByName(List<String> usernames);

    @Select("select img from user where username = #{username}")
    public String queryImgByName(String username);
}
