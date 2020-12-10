package com.erhuo.dao;

import com.erhuo.pojo.Post;
import com.erhuo.pojo.PostImg;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class PostMapperImpl extends SqlSessionDaoSupport implements PostMapper {


    @Override
    public int addPost(Post post) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        int result = postMapper.addPost(post);
        if(result == 1){
            return post.getPostId();
        }
        return result;
    }

    @Override
    public int addPostImg(List<PostImg> postImgList) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        int result = postMapper.addPostImg(postImgList);
        return result;
    }

    @Override
    public List<Post> queryAll(int startIndex) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.queryAll(startIndex);
    }

    @Override
    public List<Integer> getUserLike(int userId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.getUserLike(userId);
    }

    @Override
    public List<String> getPostImage(int postId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.getPostImage(postId);
    }

    @Override
    public int addLike(int postId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.addLike(postId);
    }

    @Override
    public int addUserLike(int postId, int userId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.addUserLike(postId,userId);
    }

    @Override
    public int subLike(int postId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.subLike(postId);
    }

    @Override
    public int delUserLike(int postId, int userId) {
        PostMapper postMapper = getSqlSession().getMapper(PostMapper.class);
        return postMapper.delUserLike(postId,userId);
    }
}
