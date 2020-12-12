package com.erhuo.dao;

import com.erhuo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface UserMapper {

    @Select("select * from user where user_id = #{userId}")
    public User queryUserById(int userId);

    public User login(@Param("username") String username, @Param("password") String password);

    public void updateDate(@Param("id") int id, @Param("date") Date date);

    public void logon(@Param("username") String username,@Param("password")String password);

    public int checkUserByUsername(String username);

    public void updateUser(User user);

    public User findUserById(@Param("id") int id);

    public void updatePassword(@Param("username") String username,
                               @Param("oldPassword") String oldPassword,
                               @Param("newPassword") String newPassword);
}
