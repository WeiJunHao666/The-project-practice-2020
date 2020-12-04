package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Commodity;
import com.erhuo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/all",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String findCommodityAll(){
        List<Commodity> all = commodityService.findAll();
        String s = JSON.toJSONString(all);
        return s;
    }

    @RequestMapping(value="/one", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String findCommodityAll(int id){
        Commodity commodity = commodityService.findCommodityById(id);
        String s = JSON.toJSONString(commodity);
        return s;
    }
}
