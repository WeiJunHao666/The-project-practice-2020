package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Post;
import com.erhuo.pojo.ServiceToSer;
import com.erhuo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/post",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/addPost")
    public String addPost(@RequestBody String postJSON){
        ServiceToSer serviceToSer = JSON.parseObject(postJSON,ServiceToSer.class);
        Post post = new Post();
        post.setUserId(serviceToSer.getUserId());
        post.setImgs(serviceToSer.getImgs());
        post.setPostMes(serviceToSer.getContent());
        post.setPostDate(serviceToSer.getDate());
        int result = postService.addPost(post);

        return result + "";
    }

    @RequestMapping("/all/{pageIndex}/{userId}")
    public String getAll(@PathVariable int pageIndex,@PathVariable int userId){
        List<Post> postList = postService.getAll(pageIndex,userId);
        String str = JSON.toJSONString(postList);
        return str;
    }

    @RequestMapping("/like/{postId}/{userId}")
    public void like(@PathVariable int postId,@PathVariable int userId){
        postService.like(postId,userId);
    }

    @RequestMapping("/unlike/{postId}/{userId}")
    public void unLike(@PathVariable int postId,@PathVariable int userId){
        postService.unLike(postId, userId);
    }

    @RequestMapping("/{pageIndex}/{userId}")
    public String getPostByUser(@PathVariable int pageIndex,@PathVariable int userId){
        List<Post> postList = postService.getPostByUser(pageIndex,userId);
        return JSON.toJSONString(postList);
    }

}
