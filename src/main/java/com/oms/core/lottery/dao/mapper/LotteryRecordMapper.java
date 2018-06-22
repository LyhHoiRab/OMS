package com.oms.core.lottery.dao.mapper;

import com.oms.core.lottery.entity.LotteryRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface LotteryRecordMapper{

    void save(LotteryRecord record);

    void update(LotteryRecord record);

    LotteryRecord get(@Param("params") Criteria criteria);

    List<LotteryRecord> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
