package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.Logistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface LogisticsMapper{

    List<Logistics> find(@Param("params") Criteria criteria);
}
