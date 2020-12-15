package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Commodity;
import com.erhuo.pojo.Enum;
import com.erhuo.service.CommodityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.Integer.min;

@Controller
@RequestMapping("commodity")
//@SessionAttributes("list")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/all",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    // TODO: 按地址搜索 参数：Integet page，String city
    public String findCommodityAll(Integer page){
        return commodityService.findAll(page,"");
    }

    @RequestMapping(value="/one", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String findCommodityById(Integer id,String city){
        List<Commodity> list = commodityService.findCommodityById(id,city);
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping(value="/search", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String searchCommodityByName(String name,String city) {
        List<Commodity> list = commodityService.findCommodityByName(name==null?null:"%"+name+"%",city);
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addCommodity(@RequestBody Commodity commodity) {
        commodityService.addCommodity(commodity);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteCommodityById(String commodityId,String username,String password) {
        commodityService.deleteCommodityById(Integer.parseInt(commodityId),username,password);
    }

    @RequestMapping(value="/searchByType",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String searchCommodityByType(Integer typeId,Integer page,String city) {
        List<Commodity> list = commodityService.searchCommodityByType(typeId,page,city);
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/store")
    @ResponseBody
    public void store(int buyerId,int sellerId,int commodityId) {
        commodityService.store(buyerId,sellerId,commodityId);
    }

    @RequestMapping("/getStore")
    @ResponseBody
    public String getStore(int buyerId,int commodityId) {
        return commodityService.getStore(buyerId,commodityId);
    }

    @RequestMapping("/deleteStore")
    @ResponseBody
    public void deleteStore(int buyerId,int commodityId) {
        commodityService.deleteStore(buyerId,commodityId);
    }

    @RequestMapping(value="/screen",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String screenCommodity(@RequestBody Enum screenEnum) {
        return commodityService.screenCommodity(screenEnum);
    }

    @RequestMapping(value="/myCommodity",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String myCommodity(int userId,int page) {
        return commodityService.myCommodity(userId,page);
    }

    @RequestMapping(value="/myStore",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String myStore(int userId,int page) {
        return commodityService.myStore(userId,page);
    }
}
