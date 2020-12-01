package com.erhuo.dao;

import com.erhuo.pojo.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    public List<Comment> queryLastIdNotNull(int postId);

    public List<Comment> queryLastIdNull(int postId);

}
