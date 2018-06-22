package com.oms.core.lottery.dao.mapper;

import com.oms.core.lottery.entity.Lottery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface LotteryMapper{

    void save(Lottery lottery);

    void update(Lottery lottery);

    Lottery get(@Param("params") Criteria criteria);

    List<Lottery> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
