package com.oms.core.statistics.dao.mapper;

import com.oms.core.statistics.entity.WechatAchievement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface WechatAchievementMapper{

    List<WechatAchievement> find(@Param("params") Criteria criteria);
}
