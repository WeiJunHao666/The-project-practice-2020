package com.erhuo.dao;

import com.erhuo.pojo.Commodity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class CommodityMapperImpl extends SqlSessionDaoSupport implements CommodityMapper {

    @Override
    public List<Commodity> findAll(int st,int ed) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Commodity> all = commodityMapper.findAll(st,ed);
        return all;
    }

    @Override
    public Commodity findCommodityById(int id) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        Commodity commodity = commodityMapper.findCommodityById(id);
        return commodity;
    }

    @Override
    public List<Commodity> findCommodityByName(String name) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Commodity> list = commodityMapper.findCommodityByName(name);
        return list;
    }

    @Override
    public void addCommodity(Commodity commodity) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        commodityMapper.addCommodity(commodity);
    }

    @Override
    public List<Commodity> searchCommodityByType(int typeId,int st,int ed) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Commodity> list = commodityMapper.searchCommodityByType(typeId,st,ed);
        return list;
    }

    @Override
    public List<Commodity> searchSimilarCommodityByType(int typeId,int st,int ed,int commodityId) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Commodity> list = commodityMapper.searchSimilarCommodityByType(typeId,st,ed,commodityId);
        return list;
    }

    @Override
    public List<Integer> searchNumberSome() {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        List<Integer> list = commodityMapper.searchNumberSome();
        return list;
    }

    @Override
    public void deleteCommodityById(int id,String username,String password) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        commodityMapper.deleteCommodityById(id,username,password);
    }

    @Override
    public void store(int buyerId, int sellerId, int commodityId) {
        CommodityMapper commodityMapper = getSqlSession().getMapper(CommodityMapper.class);
        commodityMapper.store(buyerId,sellerId,commodityId);
    }

    @Override
    public String getStore(int buyerId, int commodityId) {
        return getSqlSession().getMapper(CommodityMapper.class).getStore(buyerId,commodityId);
    }

    @Override
    public void deleteStore(int buyerId, int commodityId) {
        getSqlSession().getMapper(CommodityMapper.class).deleteStore(buyerId,commodityId);
    }
}
