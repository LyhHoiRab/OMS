package com.oms.core.popularize.dao.mapper;

import com.oms.core.popularize.entity.WxInfo;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxInfoMapper{

    void save(WxInfo wxInfo);

    void update(WxInfo wxInfo);

    WxInfo get(@Param("params") Criteria criteria);

    List<WxInfo> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
