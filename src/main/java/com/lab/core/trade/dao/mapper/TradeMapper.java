package com.lab.core.trade.dao.mapper;

import com.lab.core.trade.entity.Trade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface TradeMapper{

    List<Trade> get(@Param("params") Criteria criteria);

    List<Trade> find(@Param("params") Criteria criteria);

    List<Trade> page(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
