package com.erhuo.controller;

import com.erhuo.dao.AdviertisementMapper;
import com.erhuo.pojo.Adviertisement;
import com.erhuo.service.AdviertisementService;
import com.qiniu.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.*;
import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/adviertisement")
public class AdviertisementController {

    @Autowired
    private AdviertisementService adviertisementService;

    @RequestMapping(value = "/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getAdviertisementAll(){
        List<Adviertisement> list = adviertisementService.queryAdviertisementAll();
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAdviertisement(Adviertisement adviertisement){
        System.out.println(adviertisement);
        adviertisementService.addAdviertisement(adviertisement);
    }
}
