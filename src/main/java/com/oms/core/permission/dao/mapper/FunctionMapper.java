package com.oms.core.permission.dao.mapper;

import com.oms.core.permission.entity.Function;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionMapper{

    void saveList(List<Function> list);

    void updateList(List<Function> list);

    void deleteList(List<Function> list);

    List<Function> find(@Param("params") Criteria criteria);
}
