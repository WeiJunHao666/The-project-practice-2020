package com.erhuo.dao;

import com.erhuo.pojo.Commodity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class CommodityMapperImpl extends SqlSessionDaoSupport implements CommodityMapper {

    @Override
    public List<Commodity> findAll() {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Commodity> all = commodityMapper.findAll();
        return all;
    }

    @Override
    public Commodity findCommodityById(int id) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        Commodity commodity = commodityMapper.findCommodityById(id);
        return commodity;
    }
}
