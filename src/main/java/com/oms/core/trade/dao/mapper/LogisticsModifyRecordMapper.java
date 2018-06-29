package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.LogisticsModifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface LogisticsModifyRecordMapper{

    List<LogisticsModifyRecord> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
