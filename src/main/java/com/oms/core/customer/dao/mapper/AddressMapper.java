package com.oms.core.customer.dao.mapper;

import com.oms.core.customer.entity.Address;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper{

    void save(Address address);

    void update(Address address);

    void updateIsDefault(@Param("isDefault") Boolean isDefault, @Param("params") Criteria criteria);

    Address get(@Param("params") Criteria criteria);

    List<Address> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
