package com.erhuo.dao;

import com.erhuo.pojo.Comment;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class CommentMapperImpl extends SqlSessionDaoSupport implements CommentMapper {

    public List<Comment> queryLastIdNotNull(int postId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        List<Comment> commentList = commentMapper.queryLastIdNotNull(postId);
        return commentList;
    }

    public List<Comment> queryLastIdNull(int postId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        List<Comment> commentList = commentMapper.queryLastIdNull(postId);
        return commentList;
    }
}
