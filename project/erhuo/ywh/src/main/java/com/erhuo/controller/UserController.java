package com.erhuo.controller;

import com.erhuo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/user",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/img/{username}")
    public String getImgByName(@PathVariable String username){
        String img = userMapper.queryImgByName(username);
        return img;
    }
}
