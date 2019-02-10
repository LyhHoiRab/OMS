package com.oms.core.account.dao;

import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
import com.wah.mybatis.helper.criteria.Criteria;
import com.wah.mybatis.helper.criteria.Restrictions;
import com.oms.commons.consts.AccountStatus;
import com.oms.commons.consts.CacheName;
import com.oms.core.account.dao.mapper.AccountMapper;
import com.oms.core.account.entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Repository
public class AccountDao{

    private Logger logger = LoggerFactory.getLogger(AccountDao.class);

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private StringRedisTemplate template;

    public void saveOrUpdate(Account account){
        try{
            Assert.notNull(account, "账户信息不能为空");

            if(StringUtils.isBlank(account.getId())){
                Assert.hasText(account.getUsername(), "账户名不能为空");
                Assert.hasText(account.getPassword(), "账户密码不能为空");

                account.setId(IDUtils.uuid32());
                account.setStatus(AccountStatus.NORMAL);
                account.setCreateTime(new Date());
                mapper.save(account);
            }else{
                account.setUpdateTime(new Date());
                mapper.update(account);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean existByUsername(String username){
        try{
            Assert.hasText(username, "账户名不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("username").eq(username));

            return mapper.count(criteria) > 0;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Account getByUsername(String username){
        try{
            Assert.hasText(username, "账户名不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("username").eq(username));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void grant(String accountId, List<String> roleIds){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            //删除旧角色
            mapper.revoke(accountId);

            //添加新角色
            if(!roleIds.isEmpty()){
                mapper.grant(accountId, roleIds);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Boolean existCacheById(String id){
        try{
            Assert.hasText(id, "ID不能为空");

            return template.hasKey(CacheName.ACCOUNT_COOKIE + id);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void clearCacheById(String id){
        try{
            Assert.hasText(id, "ID不能为空");

            template.delete(CacheName.ACCOUNT_COOKIE + id);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
