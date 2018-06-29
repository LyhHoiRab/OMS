package com.oms.core.statistics.dao;

import com.oms.core.statistics.dao.mapper.SellerAchievementMapper;
import com.oms.core.statistics.entity.SellerAchievement;
import com.oms.core.trade.consts.TradeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.DateUtils;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class SellerAchievementDao{

    private Logger logger = LoggerFactory.getLogger(SellerAchievementDao.class);

    @Autowired
    private SellerAchievementMapper mapper;

    public List<SellerAchievement> find(Date minDateCreated, Date maxDateCreated, List<Long> sellers){
        try{
            Criteria criteria = new Criteria();

            criteria.groupBy(Restrictions.groupBy("pUserId"));

            criteria.sort(Restrictions.desc("price"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNotNull("pUserId"));

            if(minDateCreated != null){
                minDateCreated = DateUtils.firstTimeOfDay(minDateCreated);

                criteria.and(Restrictions.ge("dateCreated", minDateCreated));
            }
            if(maxDateCreated != null){
                maxDateCreated = DateUtils.lastTimeOfDay(maxDateCreated);

                criteria.and(Restrictions.le("dateCreated", maxDateCreated));
            }
            if(sellers != null && !sellers.isEmpty()){
                criteria.and(Restrictions.in("pUserId", sellers));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
