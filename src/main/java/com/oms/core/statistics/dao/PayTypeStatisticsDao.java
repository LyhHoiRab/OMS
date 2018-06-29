package com.oms.core.statistics.dao;

import com.oms.core.statistics.dao.mapper.PayTypeStatisticsMapper;
import com.oms.core.statistics.entity.PayTypeStatistics;
import com.oms.core.trade.consts.PayType;
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
public class PayTypeStatisticsDao{

    private Logger logger = LoggerFactory.getLogger(PayTypeStatisticsDao.class);

    @Autowired
    private PayTypeStatisticsMapper mapper;

    public List<PayTypeStatistics> findGroupBySeller(PayType payType, Date minDateCreated, Date maxDateCreated, List<Long> sellers){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("pUserId"));
            criteria.groupBy(Restrictions.groupBy("payType"));
            criteria.groupBy(Restrictions.groupBy("pUserId"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNotNull("pUserId"));

            if(payType != null){
                criteria.and(Restrictions.eq("payType", payType.getId()));
            }
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

    public List<PayTypeStatistics> findGroupBySellerAndWxNo(PayType payType, Date minDateCreated, Date maxDateCreated, List<Long> sellers, List<String> wxNos){
        try{
            Criteria criteria = new Criteria();

            criteria.sort(Restrictions.desc("pUserId"));

            criteria.groupBy(Restrictions.groupBy("payType"));
            criteria.groupBy(Restrictions.groupBy("pUserId"));
            criteria.groupBy(Restrictions.groupBy("wxNo"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNotNull("pUserId"));

            if(payType != null){
                criteria.and(Restrictions.eq("payType", payType.getId()));
            }
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
            if(wxNos != null && !wxNos.isEmpty()){
                criteria.and(Restrictions.in("wxNo", wxNos));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
