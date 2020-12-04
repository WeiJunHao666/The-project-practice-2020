package com.erhuo.dao;

import com.erhuo.pojo.CommodityImg;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class CommodityImgMapperImpl extends SqlSessionDaoSupport implements CommodityImgMapper {
    @Override
    public List<CommodityImg> findImgById(int id) {
        CommodityImgMapper commodityImgMapper = getSqlSession().getMapper(CommodityImgMapper.class);
        List<CommodityImg> img = commodityImgMapper.findImgById(id);
        return img;
    }
}
