package com.erhuo.dao;

import com.erhuo.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    public List<Commodity> findAll(@Param("st") int st,@Param("ed") int ed);

    public Commodity findCommodityById(@Param("id") int id);

    public List<Commodity> findCommodityByName(@Param("name") String name);

    public void addCommodity(Commodity commodity);

    public List<Commodity> searchCommodityByType(@Param("typeId") int typeId,@Param("st") int st,@Param("ed") int ed);

    public List<Commodity> searchSimilarCommodityByType(@Param("typeId") int typeId,@Param("st") int st,@Param("ed") int ed,@Param("commodityId") int commodityId);

    public List<Integer> searchNumberSome();

    public void deleteCommodityById(@Param("id") int id,@Param("username") String username,@Param("password") String password);

    public void store(@Param("buyerId") int buyerId, @Param("sellerId") int sellerId, @Param("commodityId") int commodityId);

    public String getStore(@Param("buyerId") int buyerId,@Param("commodityId") int commodityId);

    public void deleteStore(@Param("buyerId") int buyerId,@Param("commodityId") int commodityId);
}
