package com.oms.core.product.dao.mapper;

import com.oms.core.product.entity.Item;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper{

    void saveList(List<Item> list);

    void updateList(List<Item> list);

    List<Item> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);

    List<Item> findWithProduct(@Param("params") Criteria criteria);

    long  countWithProduct(@Param("params") Criteria criteria);
}
