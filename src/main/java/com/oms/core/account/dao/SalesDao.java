package com.oms.core.account.dao;

import com.oms.commons.consts.AccountStatus;
import com.oms.commons.consts.PermissionConfig;
import com.oms.commons.consts.helper.AccountStatusHelper;
import com.oms.core.account.dao.mapper.SalesMapper;
import com.oms.core.account.entity.User;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.Page;
import com.wah.doraemon.utils.PageRequest;
import com.wah.mybatis.helper.criteria.Criteria;
import com.wah.mybatis.helper.criteria.Restrictions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class SalesDao{

    private Logger logger = LoggerFactory.getLogger(SalesDao.class);

    @Autowired
    private SalesMapper mapper;

    public void updateStatus(List<String> accountIds, AccountStatus status){
        try{
            Assert.notEmpty(accountIds, "账户ID列表不能为空");
            Assert.notNull(status, "账户状态不能为空");

            mapper.updateStatus(accountIds, status);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            Criteria criteria = new Criteria();
            //销售角色
            criteria.and(Restrictions.where("ra.roleId").eq(PermissionConfig.SALES_ID));
            criteria.and(Restrictions.where("u.accountId").eq(accountId));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<User> page(PageRequest pageRequest, String username, String nickname, AccountStatus status){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest));
            criteria.orderBy(Restrictions.asc("a.username"));

            //销售角色
            criteria.and(Restrictions.where("ra.roleId").eq(PermissionConfig.SALES_ID));

            if(StringUtils.isNotBlank(username)){
                criteria.and(Restrictions.where("a.username").eq(username));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.where("u.nickname").eq(nickname));
            }
            if(status != null){
                criteria.and(Restrictions.where("a.status").eq(status, new AccountStatusHelper()));
            }

            List<User> list  = mapper.find(criteria);
            long       total = mapper.count(criteria);

            return new Page<User>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<User> find(String username, String nickname, AccountStatus status){
        try{
            Criteria criteria = new Criteria();
            criteria.orderBy(Restrictions.asc("a.username"));

            //销售角色
            criteria.and(Restrictions.where("ra.roleId").eq(PermissionConfig.SALES_ID));

            if(StringUtils.isNotBlank(username)){
                criteria.and(Restrictions.where("a.username").eq(username));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.where("u.nickname").eq(nickname));
            }
            if(status != null){
                criteria.and(Restrictions.where("a.status").eq(status, new AccountStatusHelper()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
