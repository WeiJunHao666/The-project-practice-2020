package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.SuperType;
import com.erhuo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/all",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String findTypeAll(){
        String s = typeService.findTypeAll();
        return s;
    }
}
