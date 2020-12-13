package com.erhuo.dao;

import com.erhuo.pojo.Post;
import com.erhuo.pojo.PostImg;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface PostMapper {

    public int addPost(Post post);

    public int addPostImg(@Param("postImgList") List<PostImg> postImgList);

    @Select("select * from post limit #{startIndex},5")
    public List<Post> queryAll(int startIndex);

    @Select("select post_id from user_like_post where user_id = #{userId}")
    List<Integer> getUserLike(int userId);

    @Select("select img from post_image where post_id = #{postId}")
    List<String> getPostImage(int postId);

    @Update("update post set like_num = like_num + 1 where post_id = #{postId} ")
    int addLike(int postId);

    @Insert("insert into user_like_post(post_id,user_id) values (#{postId},#{userId})")
    int addUserLike(@Param("postId") int postId,@Param("userId") int userId);

    @Update("update post set like_num = like_num - 1 where post_id = #{postId}")
    int subLike(int postId);

    @Delete("delete from user_like_post where post_id = #{postId} and user_id = #{userId}")
    int delUserLike(@Param("postId") int postId,@Param("userId") int userId);

    @Update("update post set view_num = view_num + 1 where post_id = #{postId}")
    int addView(int postId);
}
