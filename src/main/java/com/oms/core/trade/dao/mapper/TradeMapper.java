package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface TradeMapper{

    Trade get(@Param("params") Criteria criteria);

    List<Trade> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
