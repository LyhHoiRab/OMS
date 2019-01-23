package com.oms.core.account.dao.mapper;

import com.wah.mybatis.helper.criteria.Criteria;
import com.oms.core.account.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper{

    void save(User user);

    void update(User user);

    User get(@Param("params") Criteria criteria);

    List<User> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
