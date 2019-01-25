package com.oms.core.express.dao.mapper;

import com.oms.core.express.entity.Express;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpressMapper{

    void save(Express express);

    void update(Express express);

    Express get(@Param("params") Criteria criteria);

    List<Express> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
