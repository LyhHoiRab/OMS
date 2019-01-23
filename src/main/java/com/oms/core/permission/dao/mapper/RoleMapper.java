package com.oms.core.permission.dao.mapper;

import com.wah.mybatis.helper.criteria.Criteria;
import com.oms.core.permission.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper{

    void save(Role role);

    void update(Role role);

    void grant(@Param("roleId") String roleId, @Param("functionIds") List<String> functionIds);

    void revoke(String roleId);

    long count(@Param("params") Criteria criteria);

    Role get(@Param("params") Criteria criteria);

    List<Role> find(@Param("params") Criteria criteria);

    List<Role> findByAccountId(String accountId);

    List<Role> findWithFunctions(@Param("params") Criteria criteria);
}
