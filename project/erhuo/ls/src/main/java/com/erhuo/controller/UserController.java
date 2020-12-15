package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.User;
import com.erhuo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String login(String username,String password) {
        String login = userService.login(username, password);
        return login;
    }

    @RequestMapping(value = "/logon",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String logon(String username,String password) {
        String logon = userService.logon(username, password);
        if(logon.equals("ok")) return userService.login(username, password);
        return logon;
    }

    @RequestMapping("/update")
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String username,String oldPassword,String newPassword) {
        return userService.updatePassword(username,oldPassword,newPassword);
    }

    @RequestMapping(value = "/map",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String map(String school) {
        return userService.map(school);
    }

    @RequestMapping(value = "/city",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String city(){
        return userService.city();
    }

    @RequestMapping(value = "/nickname",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String getNickname(@RequestBody User[] user) {
        List<String> list = new ArrayList<>();
        for (User user1 : user) {
            list.add(user1.getUsername());
        }
        return userService.getNickname(list);
    }

    @RequestMapping(value = "/seller",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String seller(int userId) {
        return userService.seller(userId);
    }
}
