package com.oms.core.lottery.dao;

import com.oms.core.lottery.dao.mapper.PresentMapper;
import com.oms.core.lottery.entity.Present;
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
public class PresentDao{

    private Logger logger = LoggerFactory.getLogger(PresentDao.class);

    @Autowired
    private PresentMapper mapper;

    public void saveOrUpdate(Present present){
        try{
            Assert.notNull(present, "奖品信息不能为空");

            if(StringUtils.isBlank(present.getId())){
                Assert.hasText(present.getLotteryId(), "场次ID不能为空");
                Assert.notNull(present.getSerial(), "奖品序号不能为空");
                Assert.notNull(present.getProbability(), "奖品概率不能为空");
                Assert.notNull(present.getNumberByTime(), "奖品出现次数不能为空");
                Assert.notNull(present.getAppearByDay(), "奖品出现天数不能为空");

                present.setId(IDGenerator.uuid32());
                present.setCreateTime(new Date());
                mapper.save(present);
            }else{
                present.setUpdateTime(new Date());
                mapper.update(present);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Present getById(String id){
        try{
            Assert.hasText(id, "奖品信息不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Present> findByLotteryId(String lotteryId){
        try{
            Assert.hasText(lotteryId, "抽奖场次ID信息不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("lotteryId", lotteryId));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
