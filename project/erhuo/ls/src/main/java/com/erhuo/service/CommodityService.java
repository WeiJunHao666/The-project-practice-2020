package com.erhuo.service;

import com.alibaba.fastjson.JSON;
import com.erhuo.dao.CommodityImgMapper;
import com.erhuo.dao.CommodityMapper;
import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.Commodity;
import com.erhuo.pojo.CommodityImg;
import com.erhuo.pojo.User;
import com.erhuo.util.JedisUtil;
import com.erhuo.util.VariableName;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private CommodityImgMapper commodityImgMapper;
    @Autowired
    private UserMapper userMapper;
    // 主页商品数据 分页显示
    public String findAll(int page){
        int st,ed;
        st = page * 20;
        ed = (page + 1) * 20;
        List<Commodity> all = commodityMapper.findAll(st, ed);
        for (Commodity commodity : all) {
            //获取商品图片
            List<CommodityImg> img = commodityImgMapper.findImgById(commodity.getId());
            for (CommodityImg commodityImg : img) {
                commodityImg.setImg(VariableName.domain + "/" + commodityImg.getImg());
            }
            commodity.setImg(img);
            //获取用户信息
            commodity.getUser().setImg(VariableName.domain+"/"+commodity.getUser().getImg());
        }
        return JSON.toJSONString(all);
    }
    //根据id查询商品 用于商品详情页 同时搜出相似商品
    public List<Commodity> findCommodityById(int id) {
        Commodity commodity = commodityMapper.findCommodityById(id);
        List<Commodity> list = commodityMapper.searchSimilarCommodityByType(commodity.getTypeId(),0,6,commodity.getId());
        list.add(0,commodity);
        for (Commodity commodity1 : list) {
            int commodityId = commodity1.getId();
            List<CommodityImg> img = commodityImgMapper.findImgById(commodityId);
            for (CommodityImg commodityImg : img) {
                String s = commodityImg.getImg();
                commodityImg.setImg(VariableName.domain + "/" + s);
            }
            commodity1.setImg(img);
            commodity1.getUser().setImg(VariableName.domain+"/"+commodity1.getUser().getImg());
        }
        return list;
    }
    // 查询商品
    public List<Commodity> findCommodityByName(String name) {
        List<Commodity> list = commodityMapper.findCommodityByName(name);
        for (Commodity commodity : list) {
            int id = commodity.getId();
            List<CommodityImg> img = commodityImgMapper.findImgById(id);
            for (CommodityImg commodityImg : img) {
                String s = commodityImg.getImg();
                commodityImg.setImg(VariableName.domain + "/" + s);
            }
            commodity.setImg(img);
        }
        return list;
    }
    // 添加商品
    public void addCommodity(Commodity commodity) {
        commodity.setTime(new Date().getTime());
        commodityMapper.addCommodity(commodity);
        int id = commodity.getId();
        for (CommodityImg commodityImg : commodity.getImg()) {
            commodityImg.setCommodityId(id);
            commodityImgMapper.addImg(commodityImg);
        }
    }
    // 按类型查询商品
    public List<Commodity> searchCommodityByType(int typeId,int page) {
        List<Commodity> list = commodityMapper.searchCommodityByType(typeId,page*20,(page+1)*20);
        System.out.println(list);
        for (Commodity commodity : list) {
            int id = commodity.getId();
            List<CommodityImg> img = commodityImgMapper.findImgById(id);
            for (CommodityImg commodityImg : img) {
                String s = commodityImg.getImg();
                commodityImg.setImg(VariableName.domain + "/" + s);
            }
            commodity.setImg(img);
        }
        return list;
    }
    // 随机查询部分商品id 效率较慢 暂时弃用
    public List<Integer> searchNumberSome(){
        List<Integer> list = commodityMapper.searchNumberSome();
        return list;
    }
    // 删除商品
    public void deleteCommodityById(int id,String username,String password) {
        commodityMapper.deleteCommodityById(id,username,password);
    }

    public void store(int buyerId,int sellerId,int commodityId) {
        commodityMapper.store(buyerId,sellerId,commodityId);
    }

    public String getStore(int buyerId,int commodityId) {
        return commodityMapper.getStore(buyerId,commodityId);
    }

    public void deleteStore(int buyerId,int commodityId) {
        commodityMapper.deleteStore(buyerId,commodityId);
    }
}
