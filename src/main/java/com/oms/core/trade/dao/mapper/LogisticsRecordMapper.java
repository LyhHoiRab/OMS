package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.LogisticsRecord;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsRecordMapper{

    void save(LogisticsRecord record);

    void update(LogisticsRecord record);

    LogisticsRecord get(@Param("params") Criteria criteria);

    List<LogisticsRecord> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
