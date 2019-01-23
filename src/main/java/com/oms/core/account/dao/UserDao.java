package com.oms.core.account.dao;

import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.GsonUtils;
import com.wah.doraemon.utils.IDUtils;
import com.wah.mybatis.helper.criteria.Criteria;
import com.wah.mybatis.helper.criteria.Restrictions;
import com.oms.commons.consts.CacheName;
import com.oms.core.account.dao.mapper.UserMapper;
import com.oms.core.account.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Repository
public class UserDao{

    private Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private UserMapper mapper;

    @Autowired
    private StringRedisTemplate template;

    public void saveOrUpdate(User user){
        try{
            Assert.notNull(user, "用户信息不能为空");
            Assert.hasText(user.getAccountId(), "账户ID不能为空");

            if(StringUtils.isBlank(user.getId())){
                user.setId(IDUtils.uuid32());
                user.setCreateTime(new Date());
                mapper.save(user);
            }else{
                user.setUpdateTime(new Date());
                mapper.update(user);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("accountId").eq(accountId));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void cache(User user){
        try{
            Assert.notNull(user, "用户信息不能为空");
            Assert.hasText(user.getAccountId(), "账户ID不能为空");

            template.opsForValue().set(CacheName.ACCOUNT_COOKIE + user.getAccountId(), GsonUtils.serialize(user), CacheName.COOKIE_TIME_OUT, TimeUnit.SECONDS);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
