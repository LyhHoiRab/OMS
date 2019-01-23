package com.oms.core.product.dao.mapper;

import com.oms.core.product.entity.Product;
import com.wah.mybatis.helper.criteria.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper{

    void save(Product product);

    void update(Product product);

    void saveList(List<Product> list);

    Product get(@Param("params") Criteria criteria);

    List<Product> find(@Param("params") Criteria criteria);

    long count(@Param("params") Criteria criteria);
}
