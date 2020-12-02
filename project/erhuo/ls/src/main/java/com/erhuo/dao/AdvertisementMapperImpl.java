package com.erhuo.dao;

import com.erhuo.pojo.Advertisement;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class AdvertisementMapperImpl extends SqlSessionDaoSupport implements AdvertisementMapper {
    public List<Advertisement> queryAdvertisementAll(){
        AdvertisementMapper advertisementMapper = getSqlSession().getMapper(AdvertisementMapper.class);
        List<Advertisement> list = advertisementMapper.queryAdvertisementAll();
        return list;
    }

    @Override
    public void addAdvertisement(Advertisement advertisement) {
        SqlSession sqlSession = getSqlSession();
        AdvertisementMapper advertisementMapper = sqlSession.getMapper(AdvertisementMapper.class);
        advertisementMapper.addAdvertisement(advertisement);
    }
}
