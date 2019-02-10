package com.oms.core.account.dao.mapper;

import com.oms.commons.consts.AccountStatus;
import com.oms.core.account.entity.User;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesMapper{

    void updateStatus(@Param("accountIds") List<String> accountIds, @Param("status") AccountStatus status);

    User get(@Param("params") Criteria criteria);

    List<User> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
