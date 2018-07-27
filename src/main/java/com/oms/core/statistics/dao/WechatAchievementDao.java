package com.oms.core.statistics.dao;

import com.oms.core.statistics.dao.mapper.WechatAchievementMapper;
import com.oms.core.statistics.entity.WechatAchievement;
import com.oms.core.trade.consts.TradeType;
import org.apache.commons.lang3.StringUtils;
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
public class WechatAchievementDao{

    private Logger logger = LoggerFactory.getLogger(WechatAchievementDao.class);

    @Autowired
    private WechatAchievementMapper mapper;

    public List<WechatAchievement> find(Date minDateCreated, Date maxDateCreated, List<Long> sellers, String wxNo){
        try{
            Criteria criteria = new Criteria();

            criteria.groupBy(Restrictions.groupBy("wxNo"));
            criteria.groupBy(Restrictions.groupBy("pUserId"));

            criteria.sort(Restrictions.desc("pUserId"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNotNull("pUserId"));

            if(minDateCreated != null){
                minDateCreated = DateUtils.firstTimeOfDate(minDateCreated);

                criteria.and(Restrictions.ge("dateCreated", minDateCreated));
            }
            if(maxDateCreated != null){
                maxDateCreated = DateUtils.lastTimeOfDate(maxDateCreated);

                criteria.and(Restrictions.le("dateCreated", maxDateCreated));
            }
            if(sellers != null && !sellers.isEmpty()){
                criteria.and(Restrictions.in("pUserId", sellers));
            }
            if(StringUtils.isNotBlank(wxNo)){
                criteria.and(Restrictions.like("wxNo", wxNo));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
