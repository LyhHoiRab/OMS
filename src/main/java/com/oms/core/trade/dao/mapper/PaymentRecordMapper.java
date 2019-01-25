package com.oms.core.trade.dao.mapper;

import com.oms.core.trade.entity.PaymentRecord;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRecordMapper{

    void save(PaymentRecord record);

    void update(PaymentRecord record);

    PaymentRecord get(@Param("params") Criteria criteria);

    List<PaymentRecord> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
