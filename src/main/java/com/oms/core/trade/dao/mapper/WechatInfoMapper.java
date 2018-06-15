package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.WechatInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface WechatInfoMapper{

    List<WechatInfo> find(@Param("params") Criteria criteria);
}
