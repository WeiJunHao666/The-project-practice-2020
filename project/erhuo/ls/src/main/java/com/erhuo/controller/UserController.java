package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.User;
import com.erhuo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String login(String username,String password) {
        User user = userService.login(username, password);
        if(user==null) {
            return "";
        }
        String s = JSON.toJSONString(user);
        return s;
    }

    @RequestMapping("logon")
    @ResponseBody
    public String logon(String username,String password) {
        String logon = userService.logon(username, password);
        return logon;
    }
}
