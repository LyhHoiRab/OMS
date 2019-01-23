package com.oms.core.account.dao.mapper;

import com.wah.mybatis.helper.criteria.Criteria;
import com.oms.core.account.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper{

    void save(Account account);

    void update(Account account);

    void grant(@Param("accountId") String accountId, @Param("roleIds") List<String> roleIds);

    void revoke(String accountId);

    void count(String accountId);

    Account get(@Param("params") Criteria criteria);

    List<Account> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
