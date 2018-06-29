package com.oms.core.trade.dao;

import com.oms.core.trade.dao.mapper.TradeModifyRecordMapper;
import com.oms.core.trade.entity.TradeModifyRecord;
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
public class TradeModifyRecordDao{

    private Logger logger = LoggerFactory.getLogger(TradeModifyRecordDao.class);

    @Autowired
    private TradeModifyRecordMapper mapper;

    public Page<TradeModifyRecord> page(PageRequest pageRequest, String tradeId, Date minOperateTime, Date maxOperateTime){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("createTime"));
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));

            if(StringUtils.isNotBlank(tradeId)){
                criteria.and(Restrictions.like("tradeId", tradeId));
            }
            if(minOperateTime != null){
                minOperateTime = DateUtils.firstTimeOfDay(minOperateTime);

                criteria.and(Restrictions.ge("operateTime", minOperateTime));
            }
            if(maxOperateTime != null){
                maxOperateTime = DateUtils.lastTimeOfDay(maxOperateTime);

                criteria.and(Restrictions.le("operateTime", maxOperateTime));
            }

            List<TradeModifyRecord> list  = mapper.find(criteria);
            Long                    total = mapper.count(criteria);

            return new Page<TradeModifyRecord>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
