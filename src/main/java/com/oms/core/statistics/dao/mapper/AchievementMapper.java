package com.oms.core.statistics.dao.mapper;

import com.oms.core.statistics.entity.Achievement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface AchievementMapper{

    List<Achievement> find(@Param("params") Criteria criteria);

    Achievement get(@Param("params") Criteria criteria);
}
