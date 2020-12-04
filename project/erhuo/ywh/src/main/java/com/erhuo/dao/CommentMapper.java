package com.erhuo.dao;

import com.erhuo.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {

    //每组评论的第一个
    public List<Comment> queryLastIdNotNull(int postId);
    //其他评论
    public List<Comment> queryLastIdNull(int postId);

    //查找用户点赞过的评论
    @Select("select user_likecom.com_id from user_likecom where user_id = #{userId}")
    public List<Integer> queryLikeCom(int userId);

    //点赞
    @Update("update comment set like_num = like_num + 1 where com_id = #{comId}")
    public int addLike(int comId);

    @Insert("insert into user_likecom(user_id,com_id) values(#{userId},#{comId})")
    public int addUserLike(@Param("userId") int userId, @Param("comId") int comId);

    //取消点赞
    @Update("update comment set like_num = like_num - 1 where com_id = #{comId}")
    public int delLike(int comId);

    @Delete("delete form user_likecom where user_id = #{userId} and com_id = #{comId}")
    public int delUserLike(@Param("userId") int userId, @Param("comId") int comId);

    int addCom(Comment comment1);

    int reply(Comment comment);
}
