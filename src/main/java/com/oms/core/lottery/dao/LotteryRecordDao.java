package com.oms.core.lottery.dao;

import com.oms.core.lottery.dao.mapper.LotteryRecordMapper;
import com.oms.core.lottery.entity.LotteryRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class LotteryRecordDao{

    private Logger logger = LoggerFactory.getLogger(LotteryRecordDao.class);

    @Autowired
    private LotteryRecordMapper mapper;

    public void saveOrUpdate(LotteryRecord record){
        try{
            Assert.notNull(record, "抽奖记录信息不能为空");

            if(StringUtils.isBlank(record.getId())){
                Assert.hasText(record.getLotteryId(), "抽奖场次ID不能为空");
                Assert.hasText(record.getOpenId(), "用户OpenId不能为空");
                Assert.notNull(record.getPresent(), "抽奖奖品不能为空");
                Assert.hasText(record.getPresent().getId(), "抽奖奖品ID不能为空");

                record.setId(IDGenerator.uuid32());
                record.setIsCash(false);
                record.setCreateTime(new Date());
            }else{
                record.setUpdateTime(new Date());
                mapper.update(record);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public LotteryRecord getByOpenIdAndLotteryId(String openId, String lotteryId){
        try{
            Assert.hasText(openId, "用户OpenId不能为空");
            Assert.hasText(lotteryId, "抽奖场次ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("openId", openId));
            criteria.and(Restrictions.eq("lotteryId", lotteryId));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<LotteryRecord> findByPresentId(String presentId){
        try{
            Assert.hasText(presentId, "抽奖奖品ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("presentId", presentId));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
