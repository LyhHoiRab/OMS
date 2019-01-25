package com.oms.core.payment.dao.mapper;

import com.oms.core.payment.entity.Payment;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMapper{

    void save(Payment payment);

    void update(Payment payment);

    Payment get(@Param("params") Criteria criteria);

    List<Payment> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
