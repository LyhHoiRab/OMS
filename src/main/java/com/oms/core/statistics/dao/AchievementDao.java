package com.oms.core.statistics.dao;

import com.oms.core.statistics.dao.mapper.AchievementMapper;
import com.oms.core.statistics.dao.mapper.PayTypeStatisticsMapper;
import com.oms.core.statistics.entity.Achievement;
import com.oms.core.statistics.entity.PayTypeStatistics;
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
public class AchievementDao{

    private Logger logger = LoggerFactory.getLogger(AchievementDao.class);

    @Autowired
    private AchievementMapper mapper;

    public Achievement achievement(Date minDateCreated, Date maxDateCreated){
        try{
            Criteria criteria = new Criteria();

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));

            if(minDateCreated != null){
                minDateCreated = DateUtils.firstTimeOfDay(minDateCreated);

                criteria.and(Restrictions.ge("dateCreated", minDateCreated));
            }
            if(maxDateCreated != null){
                maxDateCreated = DateUtils.lastTimeOfDay(maxDateCreated);

                criteria.and(Restrictions.le("dateCreated", maxDateCreated));
            }

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
