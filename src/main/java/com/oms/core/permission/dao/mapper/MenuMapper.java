package com.oms.core.permission.dao.mapper;

import com.wah.mybatis.helper.criteria.Criteria;
import com.oms.core.permission.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper{

    void save(Menu menu);

    void update(Menu menu);

    Menu get(@Param("params") Criteria criteria);

    List<Menu> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
