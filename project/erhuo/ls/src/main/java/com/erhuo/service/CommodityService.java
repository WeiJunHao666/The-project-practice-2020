package com.erhuo.service;

import com.erhuo.dao.CommodityImgMapper;
import com.erhuo.dao.CommodityMapper;
import com.erhuo.pojo.Commodity;
import com.erhuo.pojo.CommodityImg;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private CommodityImgMapper commodityImgMapper;
    public List<Commodity> findAll(){
        List<Commodity> all = commodityMapper.findAll();
        System.out.println(all);
        for (Commodity commodity : all) {
            int id = commodity.getId();
            List<CommodityImg> img = commodityImgMapper.findImgById(id);
            System.out.println(img);
            commodity.setImg(img);
        }
        System.out.println(all);
        return all;
    }

    public Commodity findCommodityById(int id) {
        Commodity commodity = commodityMapper.findCommodityById(id);
        int commodityId = commodity.getId();
        List<CommodityImg> img = commodityImgMapper.findImgById(commodityId);
        commodity.setImg(img);
        return commodity;
    }

    public List<Commodity> findCommodityByName(String name) {
        List<Commodity> list = commodityMapper.findCommodityByName(name);
        for (Commodity commodity : list) {
            int id = commodity.getId();
            List<CommodityImg> img = commodityImgMapper.findImgById(id);
            commodity.setImg(img);
        }
        return list;
    }
}
