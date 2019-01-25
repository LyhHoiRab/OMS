package com.oms.core.customer.dao.mapper;

import com.oms.core.customer.entity.Client;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientMapper{

    void save(Client client);

    void update(Client client);

    Client get(@Param("params") Criteria criteria);

    List<Client> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
