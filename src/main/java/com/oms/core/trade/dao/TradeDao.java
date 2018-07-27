package com.oms.core.trade.dao;

import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.consts.StatusType;
import com.oms.core.trade.consts.TradeType;
import com.oms.core.trade.dao.mapper.TradeMapper;
import com.oms.core.trade.entity.Trade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.DateUtils;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class TradeDao{

    private Logger logger = LoggerFactory.getLogger(TradeDao.class);

    @Autowired
    private TradeMapper mapper;

    public Page<Trade> page(PageRequest pageRequest, String wxno, String tradeId, ExpressType express, PayType payType,
                            Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                            Date maxAppointDeliveryTime, List<Long> consigneeIds, List<Long> sellers, List<String> tradeIds,
                            List<Long> boxIds, StatusType status){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("dateCreated"));
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
            if(express != null){
                criteria.and(Restrictions.eq("kdType", express.getId()));
            }
            if(payType != null){
                criteria.and(Restrictions.eq("payType", payType.getId()));
            }
            if(prepaidFee != null){
                criteria.and(Restrictions.eq("prepaidFee", prepaidFee));
            }
            if(price != null){
                criteria.and(Restrictions.eq("price", price));
            }
            if(minDateCreated != null){
                minDateCreated = DateUtils.firstTimeOfDate(minDateCreated);

                criteria.and(Restrictions.ge("dateCreated", minDateCreated));
            }
            if(maxDateCreated != null){
                maxDateCreated = DateUtils.lastTimeOfDate(maxDateCreated);

                criteria.and(Restrictions.le("dateCreated", maxDateCreated));
            }
            if(minAppointDeliveryTime != null){
                minAppointDeliveryTime = DateUtils.firstTimeOfDate(minAppointDeliveryTime);

                criteria.and(Restrictions.ge("appointDeliveryTime", minAppointDeliveryTime));
            }
            if(maxAppointDeliveryTime != null){
                maxAppointDeliveryTime = DateUtils.lastTimeOfDate(maxAppointDeliveryTime);

                criteria.and(Restrictions.le("appointDeliveryTime", maxAppointDeliveryTime));
            }
            if(consigneeIds != null && !consigneeIds.isEmpty()){
                criteria.and(Restrictions.in("consigneeId", consigneeIds));
            }
            if(sellers != null && !sellers.isEmpty()){
                criteria.and(Restrictions.in("pUserId", sellers));
            }
            if(tradeIds != null && !tradeIds.isEmpty()){
                criteria.and(Restrictions.in("tradeId", tradeIds));
            }
            if(boxIds != null && !boxIds.isEmpty()){
                criteria.and(Restrictions.in("mineScBoxId", boxIds));
            }
            if(status != null){
                criteria.and(Restrictions.eq("status", status.getId()));
            }

            List<Trade> list  = mapper.find(criteria);
            Long        total = mapper.count(criteria);

            return new Page<Trade>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Trade> pageByAppointDeliveryTimeNull(PageRequest pageRequest, String wxno, String tradeId, List<Long> sellers,
                                                     Date minDateCreated, Date maxDateCreated){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();

            criteria.sort(Restrictions.desc("dateCreated"));
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNull("appointDeliveryTime"));
            criteria.and(Restrictions.eq("status", StatusType.WATI_FOR_SENDING.getId()));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
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

            List<Trade> list  = mapper.find(criteria);
            Long        total = mapper.count(criteria);

            return new Page<Trade>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Trade> pageByStatusFailAndUnusual(PageRequest pageRequest, String wxno, String tradeId, List<Long> sellers,
                                                  Date minDateCreated, Date maxDateCreated, List<String> tradeIds, ExpressType express){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();

            criteria.sort(Restrictions.desc("dateCreated"));
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNull("appointDeliveryTime"));
            criteria.and(Restrictions.or(Restrictions.eq("status", StatusType.FAIL.getId()),
                                         Restrictions.eq("status", StatusType.UNUSUAL.getId())));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
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
            if(tradeIds != null && !tradeIds.isEmpty()){
                criteria.and(Restrictions.in("tradeId", tradeIds));
            }
            if(express != null){
                criteria.and(Restrictions.eq("kdType", express.getId()));
            }

            List<Trade> list  = mapper.find(criteria);
            Long        total = mapper.count(criteria);

            return new Page<Trade>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Trade> find(String wxno, String tradeId, ExpressType express, PayType payType, Long prepaidFee, Long price,
                            Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime, Date maxAppointDeliveryTime,
                            List<Long> consigneeIds, List<Long> sellers, List<String> tradeIds, List<Long> boxIds){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("dateCreated"));
            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
            if(express != null){
                criteria.and(Restrictions.eq("kdType", express.getId()));
            }
            if(payType != null){
                criteria.and(Restrictions.eq("payType", payType.getId()));
            }
            if(prepaidFee != null){
                criteria.and(Restrictions.eq("prepaidFee", prepaidFee));
            }
            if(price != null){
                criteria.and(Restrictions.eq("price", price));
            }
            if(minDateCreated != null){
                minDateCreated = DateUtils.firstTimeOfDate(minDateCreated);

                criteria.and(Restrictions.ge("dateCreated", minDateCreated));
            }
            if(maxDateCreated != null){
                maxDateCreated = DateUtils.lastTimeOfDate(maxDateCreated);

                criteria.and(Restrictions.le("dateCreated", maxDateCreated));
            }
            if(minAppointDeliveryTime != null){
                minAppointDeliveryTime = DateUtils.firstTimeOfDate(minAppointDeliveryTime);

                criteria.and(Restrictions.ge("appointDeliveryTime", minAppointDeliveryTime));
            }
            if(maxAppointDeliveryTime != null){
                maxAppointDeliveryTime = DateUtils.lastTimeOfDate(maxAppointDeliveryTime);

                criteria.and(Restrictions.le("appointDeliveryTime", maxAppointDeliveryTime));
            }
            if(consigneeIds != null && !consigneeIds.isEmpty()){
                criteria.and(Restrictions.in("consigneeId", consigneeIds));
            }
            if(sellers != null && !sellers.isEmpty()){
                criteria.and(Restrictions.in("pUserId", sellers));
            }
            if(tradeIds != null && !tradeIds.isEmpty()){
                criteria.and(Restrictions.in("tradeId", tradeIds));
            }
            if(boxIds != null && !boxIds.isEmpty()){
                criteria.and(Restrictions.in("mineScBoxId", boxIds));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Trade> findByAppointDeliveryTimeNull(String wxno, String tradeId, List<Long> sellers, Date minDateCreated,
                                                     Date maxDateCreated){
        try{
            Criteria criteria = new Criteria();

            criteria.sort(Restrictions.desc("dateCreated"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNull("appointDeliveryTime"));
            criteria.and(Restrictions.eq("status", StatusType.WATI_FOR_SENDING.getId()));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
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

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Trade> findByStatusFailAndUnusual(String wxno, String tradeId, List<Long> sellers, Date minDateCreated,
                                                  Date maxDateCreated, List<String> tradeIds, ExpressType express){
        try{
            Criteria criteria = new Criteria();

            criteria.sort(Restrictions.desc("dateCreated"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.isNull("appointDeliveryTime"));
            criteria.and(Restrictions.or(Restrictions.eq("status", StatusType.FAIL.getId()),
                    Restrictions.eq("status", StatusType.UNUSUAL.getId())));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxNo", wxno));
            }
            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
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
            if(tradeIds != null && !tradeIds.isEmpty()){
                criteria.and(Restrictions.in("tradeId", tradeIds));
            }
            if(express != null){
                criteria.and(Restrictions.eq("kdType", express.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Trade> findByIds(List<String> ids){
        try{
            Assert.notEmpty(ids, "订单ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("dateCreated"));

            criteria.and(Restrictions.eq("type", TradeType.CUSTOMIZE.getId()));
            criteria.and(Restrictions.in("id", ids));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
