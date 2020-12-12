package com.erhuo.controller;

import com.alibaba.fastjson.JSON;
import com.erhuo.pojo.Commodity;
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
    public String findCommodityAll(int page){
        return commodityService.findAll(page);
    }

    @RequestMapping(value="/one", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String findCommodityById(int id){
        List<Commodity> list = commodityService.findCommodityById(id);
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping(value="/search", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String searchCommodityByName(String name) {
        List<Commodity> list = commodityService.findCommodityByName("%"+name+"%");
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addCommodity(@RequestBody Commodity commodity) {
        commodityService.addCommodity(commodity);
    }

    @RequestMapping(value="/searchByType",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    @ResponseBody
    public String searchCommodityByType(int typeId,int page) {
        List<Commodity> list = commodityService.searchCommodityByType(typeId,page);
        String s = JSON.toJSONString(list);
        return s;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteCommodityById(String id,String username,String password) {
        commodityService.deleteCommodityById(Integer.parseInt(id),username,password);
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

}
