package com.oms.core.permission.dao;

import com.wah.doraemon.domain.consts.UsingStatus;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
import com.wah.doraemon.utils.ObjectUtils;
import com.wah.mybatis.helper.criteria.Criteria;
import com.wah.mybatis.helper.criteria.Restrictions;
import com.oms.commons.consts.CacheName;
import com.oms.core.permission.dao.mapper.RoleMapper;
import com.oms.core.permission.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDao{

    private Logger logger = LoggerFactory.getLogger(RoleDao.class);

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private StringRedisTemplate template;

    public void saveOrUpdate(Role role){
        try{
            Assert.notNull(role, "角色信息不能为空");

            if(StringUtils.isBlank(role.getId())){
                role.setId(IDUtils.uuid32());
                role.setStatus(UsingStatus.ENABLE);
                role.setIsSystem(false);
                role.setCreateTime(new Date());
                mapper.save(role);
            }else{
                role.setUpdateTime(new Date());
                mapper.update(role);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Role> findByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            return mapper.findByAccountId(accountId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Set<String> findCacheByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            return template.opsForSet().members(CacheName.ACCOUNT_ROLE + accountId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Role> findByIds(List<String> roleIds){
        try{
            Assert.notEmpty(roleIds, "ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").in(roleIds));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Role> find(){
        try{
            return mapper.find(null);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Role> findWithFunctions(){
        try{
            return mapper.findWithFunctions(null);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Boolean granted(String roleId, String api, String method){
        try{
            Assert.hasText(roleId, "角色ID不能为空");
            Assert.hasText(api, "功能API不能为空");
            Assert.hasText(method, "功能请求方式不能为空");

            return template.opsForSet().isMember(CacheName.ROLE_FUNCTION + roleId, api + "_" + method);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void cacheByAccountId(String accountId, List<Role> roles){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            if(!roles.isEmpty()){
                template.opsForSet().add(CacheName.ACCOUNT_ROLE + accountId, ObjectUtils.getIds(roles).toArray(new String[roles.size()]));
            }else{
                template.delete(CacheName.ACCOUNT_ROLE + accountId);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public void grant(String roleId, List<String> functionIds){
        try{
            Assert.hasText(roleId, "角色ID不能为空");

            //删除旧权限
            mapper.revoke(roleId);

            //添加新权限
            if(!functionIds.isEmpty()){
                mapper.grant(roleId, functionIds);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void clearCacheByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            template.delete(CacheName.ACCOUNT_ROLE + accountId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
