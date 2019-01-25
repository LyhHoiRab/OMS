package com.oms.core.customer.dao.mapper;

import com.oms.core.customer.entity.Report;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportMapper{

    void save(Report report);

    void update(Report report);

    Report get(@Param("params") Criteria criteria);

    List<Report> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
