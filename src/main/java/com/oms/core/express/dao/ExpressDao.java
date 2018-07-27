package com.oms.core.express.dao;

import com.oms.core.express.dao.mapper.ExpressMapper;
import com.oms.core.trade.consts.ExpressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;

import java.util.List;

@Repository
public class ExpressDao{

    private Logger logger = LoggerFactory.getLogger(ExpressDao.class);

    @Autowired
    private ExpressMapper mapper;

    public void update(List<String> ids, Integer expressId){
        try{
            Assert.notEmpty(ids, "订单ID列表不能为空");
            Assert.notNull(expressId, "快递公司ID不能为空");

            //查询快递类型
            ExpressType express = ExpressType.getById(expressId);

            //更新
            mapper.update(ids, express);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
