package com.oms.core.product.dao.mapper;

import com.oms.core.product.entity.BoxProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface BoxProductMapper{

    List<BoxProduct> find(@Param("params") Criteria criteria);
}
