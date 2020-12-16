package com.erhuo.service;

import com.erhuo.dao.PostMapper;
import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.Post;
import com.erhuo.pojo.PostImg;
import com.erhuo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    public int addPost(Post post){
        int postId = postMapper.addPost(post);
        List<PostImg> postImgList = new ArrayList<>();
        for (String img : post.getImgs()) {
            PostImg postImg = new PostImg();
            postImg.setPostId(postId);
            postImg.setImg(img);
            postImgList.add(postImg);
        }

        int result = postMapper.addPostImg(postImgList);
        return postId;
    }

    public List<Post> getAll(int pageIndex, int userId) {
        List<Post> postList = postMapper.queryAll((pageIndex - 1) * 5);
        List<Integer> likeList = postMapper.getUserLike(userId);
        for (Post post : postList) {
            if(likeList.contains(post.getPostId())){
                post.setIsLike(true);
            }else {
                post.setIsLike(false);
            }
            List<String> imgList = postMapper.getPostImage(post.getPostId());
            post.setImgs(imgList);
            User user = userMapper.queryById(post.getUserId());
            post.setUserName(user.getNickname());
            post.setUserImg(user.getImg());
        }
        return postList;
    }

    public void like(int postId, int userId) {
        postMapper.addLike(postId);
        postMapper.addUserLike(postId,userId);
    }

    public void unLike(int postId,int userId){
        postMapper.subLike(postId);
        postMapper.delUserLike(postId,userId);
    }

    public List<Post> getPostByUser(int pageIndex, int userId) {
        List<Post> postList = postMapper.getPostByUser((pageIndex - 1 ) * 20,userId);
        for (Post post : postList) {
            List<String> imgList = postMapper.getPostImage(post.getPostId());
            post.setImgs(imgList);
        }
        return postList;
    }
}
