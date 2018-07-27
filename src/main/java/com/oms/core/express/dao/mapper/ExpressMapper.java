package com.oms.core.express.dao.mapper;

import com.oms.core.trade.consts.ExpressType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExpressMapper{

    void update(@Param("ids") List<String> ids, @Param("express") ExpressType express);
}
