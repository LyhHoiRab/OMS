package com.oms.core.trade.dao;

import com.oms.core.trade.dao.mapper.OrderMapper;
import com.oms.core.trade.entity.Order;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class OrderDao{

    private Logger logger = LoggerFactory.getLogger(OrderDao.class);

    @Autowired
    private OrderMapper mapper;

    public List<Order> findByTradeIds(List<String> tradeIds){
        try{
            Assert.notEmpty(tradeIds, "订单号列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("tradeId", tradeIds));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Order> find(String tradeId, String wlnumber){
        try{
            Criteria criteria = new Criteria();

            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
            if(StringUtils.isNotBlank(wlnumber)){
                criteria.and(Restrictions.eq("wlnumber", wlnumber));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
