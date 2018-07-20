package com.oms.core.product.dao.mapper;

import com.oms.core.product.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper{

    void save(Item item);

    void update(Item item);

    Item get(@Param("params") Criteria criteria);

    List<Item> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
