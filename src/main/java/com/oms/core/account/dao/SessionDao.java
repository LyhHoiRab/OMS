package com.oms.core.account.dao;

import com.wah.doraemon.security.exception.DataAccessException;
import com.oms.commons.consts.CacheName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class SessionDao{

    private Logger logger = LoggerFactory.getLogger(SessionDao.class);

    @Autowired
    private StringRedisTemplate template;

    public void clearCacheByById(String id){
        try{
            Assert.hasText(id, "ID不能为空");

            template.delete(CacheName.ACCOUNT_SESSION + ":" + id);
            template.delete(CacheName.ACCOUNT_SESSION_EXPIRES + id);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
