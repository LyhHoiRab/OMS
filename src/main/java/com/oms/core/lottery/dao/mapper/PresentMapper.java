package com.oms.core.lottery.dao.mapper;

import com.oms.core.lottery.entity.Present;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface PresentMapper{

    void save(Present present);

    void update(Present present);

    Present get(@Param("params") Criteria criteria);

    List<Present> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
