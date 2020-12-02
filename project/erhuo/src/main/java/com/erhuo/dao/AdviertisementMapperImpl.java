package com.erhuo.dao;

import com.erhuo.pojo.Adviertisement;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class AdviertisementMapperImpl extends SqlSessionDaoSupport implements AdviertisementMapper {
    public List<Adviertisement> queryAdviertisementAll(){
        AdviertisementMapper adviertisementMapper = getSqlSession().getMapper(AdviertisementMapper.class);
        List<Adviertisement> list = adviertisementMapper.queryAdviertisementAll();
        return list;
    }

    @Override
    public void addAdviertisement(Adviertisement adviertisement) {
        SqlSession sqlSession = getSqlSession();
        AdviertisementMapper adviertisementMapper = sqlSession.getMapper(AdviertisementMapper.class);
        adviertisementMapper.addAdviertisement(adviertisement);
    }
}
