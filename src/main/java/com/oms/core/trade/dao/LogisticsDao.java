package com.oms.core.trade.dao;

import com.oms.core.trade.dao.mapper.LogisticsMapper;
import com.oms.core.trade.entity.Logistics;
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
public class LogisticsDao{

    private Logger logger = LoggerFactory.getLogger(LogisticsDao.class);

    @Autowired
    private LogisticsMapper mapper;

    public List<Logistics> findByIds(List<Long> ids){
        try{
            Assert.notEmpty(ids, "物流信息ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("id", ids));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Logistics> find(String province, String city, String district, String contactName, String phone, List<Long> ids){
        try{
            Criteria criteria = new Criteria();

            if(StringUtils.isNotBlank(province)){
                criteria.and(Restrictions.like("province", province));
            }
            if(StringUtils.isNotBlank(city)){
                criteria.and(Restrictions.like("city", city));
            }
            if(StringUtils.isNotBlank(district)){
                criteria.and(Restrictions.like("district", district));
            }
            if(StringUtils.isNotBlank(contactName)){
                criteria.and(Restrictions.like("contactName", contactName));
            }
            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.or(Restrictions.like("phone", phone), Restrictions.like("mobile", phone)));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
