package com.erhuo.dao;

import com.erhuo.pojo.Comment;
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

    public List<Integer> queryLikeCom(int userId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        List<Integer> likeList = commentMapper.queryLikeCom(userId);
        return likeList;
    }

    public int addLike(int comId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        int result = commentMapper.addLike(comId);
        return result;
    }

    public int addUserLike(int userId, int comId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        int result = commentMapper.addUserLike(userId,comId);
        return result;
    }

    public int delLike(int comId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        int result = commentMapper.delLike(comId);
        return result;
    }

    public int delUserLike(int userId, int comId) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        int result = commentMapper.delUserLike(userId,comId);
        return result;
    }

    @Override
    public int addCom(Comment comment1) {
        CommentMapper commentMapper = getSqlSession().getMapper(CommentMapper.class);
        int result = commentMapper.addCom(comment1);
        return result;
    }

    @Override
    public int reply(Comment comment) {
        return 0;
    }
}
