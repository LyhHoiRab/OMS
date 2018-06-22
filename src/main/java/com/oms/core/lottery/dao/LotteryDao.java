package com.oms.core.lottery.dao;

import com.oms.core.lottery.dao.mapper.LotteryMapper;
import com.oms.core.lottery.entity.Lottery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class LotteryDao{

    private Logger logger = LoggerFactory.getLogger(LotteryDao.class);

    @Autowired
    private LotteryMapper mapper;

    public void saveOrUpdate(Lottery lottery){
        try{
            Assert.notNull(lottery, "抽奖场次信息不能为空");

            if(StringUtils.isBlank(lottery.getId())){
                lottery.setId(IDGenerator.uuid32());
                lottery.setState(UsingState.USABLE);
                lottery.setCreateTime(new Date());
                mapper.save(lottery);
            }else{
                lottery.setUpdateTime(new Date());
                mapper.update(lottery);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Lottery getById(String id){
        try{
            Assert.hasText(id, "抽奖场次信息不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Lottery> page(PageRequest pageRequest, UsingState state){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }

            List<Lottery> list  = mapper.find(criteria);
            Long          total = mapper.count(criteria);

            return new Page<Lottery>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
