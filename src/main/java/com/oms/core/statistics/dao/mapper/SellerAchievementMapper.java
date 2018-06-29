package com.oms.core.statistics.dao.mapper;

import com.oms.core.statistics.entity.SellerAchievement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface SellerAchievementMapper{

    List<SellerAchievement> find(@Param("params") Criteria criteria);
}
