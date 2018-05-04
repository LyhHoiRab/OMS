package com.lab.core.trade.dao;

import com.lab.core.trade.consts.ExpressType;
import com.lab.core.trade.consts.OrderStateType;
import com.lab.core.trade.consts.PayType;
import com.lab.core.trade.dao.mapper.TradeMapper;
import com.lab.core.trade.entity.Trade;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 分页查询
     */
    public Page<Trade> page(PageRequest pageRequest, String tradeId, String title, Long totalFee, Long prepaidFee,
                            ExpressType expressType, PayType payType, String wxNo, String province, String city,
                            String contactName, String phone, String productName, String seller, OrderStateType stateType,
                            Date dateCreated, Date appointDeliveryTime, Date orderNumberTime){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("t.dateCreated"));

            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.eq("t.tradeId", tradeId));
            }
            if(StringUtils.isNotBlank(title)){
                criteria.and(Restrictions.like("t.title", title));
            }
            if(totalFee != null){
                criteria.and(Restrictions.eq("t.totalFee", totalFee));
            }
            if(prepaidFee != null){
                criteria.and(Restrictions.eq("t.prepaidFee", prepaidFee));
            }
            if(expressType != null){
                criteria.and(Restrictions.eq("t.kdType", expressType.getId()));
            }
            if(payType != null){
                criteria.and(Restrictions.eq("t.payType", payType.getId()));
            }
            if(StringUtils.isNotBlank(wxNo)){
                criteria.and(Restrictions.like("t.wxNo", wxNo));
            }
            if(StringUtils.isNotBlank(province)){
                criteria.and(Restrictions.eq("l.province", province));
            }
            if(StringUtils.isNotBlank(city)){
                criteria.and(Restrictions.eq("l.city", city));
            }
            if(StringUtils.isNotBlank(contactName)){
                criteria.and(Restrictions.like("l.contactName", contactName));
            }
            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.or(Restrictions.like("l.phone", phone), Restrictions.like("l.mobile", phone)));
            }
            if(StringUtils.isNotBlank(productName)){
                criteria.and(Restrictions.like("p.prodName", productName));
            }
            if(StringUtils.isNotBlank(seller)){
                criteria.and(Restrictions.like("s.realName", seller));
            }
            if(stateType != null){
                criteria.and(Restrictions.eq("o.state", stateType.getId()));
            }
            if(dateCreated != null){
                criteria.and(Restrictions.between("t.dateCreated", DateUtils.firstTimeOfDay(dateCreated), DateUtils.lastTimeOfDay(dateCreated)));
            }
            if(appointDeliveryTime != null){
                criteria.and(Restrictions.between("t.appointDeliveryTime", DateUtils.firstTimeOfDay(appointDeliveryTime), DateUtils.lastTimeOfDay(appointDeliveryTime)));
            }
            if(orderNumberTime != null){
                criteria.and(Restrictions.between("t.orderNumberTime", DateUtils.firstTimeOfDay(orderNumberTime), DateUtils.lastTimeOfDay(orderNumberTime)));
            }

            List<Trade> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Trade>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
