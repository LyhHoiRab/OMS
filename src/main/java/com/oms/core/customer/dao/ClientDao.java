package com.oms.core.customer.dao;

import com.oms.core.customer.dao.mapper.ClientMapper;
import com.oms.core.customer.entity.Client;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
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

import java.util.Date;
import java.util.List;

@Repository
public class ClientDao{

    private Logger logger = LoggerFactory.getLogger(ClientDao.class);

    @Autowired
    private ClientMapper mapper;

    public void saveOrUpdate(Client client){
        try{
            Assert.notNull(client, "客户信息不能为空");

            if(StringUtils.isBlank(client.getId())){
                Assert.hasText(client.getAccountId(), "关联账户ID不能为空");
                Assert.hasText(client.getName(), "客户姓名不能为空");
                Assert.hasText(client.getWxno(), "客户微信不能为空");

                client.setId(IDUtils.uuid32());
                client.setCreateTime(new Date());
                mapper.save(client);
            }else{
                client.setUpdateTime(new Date());
                mapper.update(client);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Client> page(PageRequest pageRequest, String name, String wxno, String remark){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest));
            criteria.orderBy(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.where("name").like(name));
            }
            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.where("wxno").like(wxno));
            }
            if(StringUtils.isNotBlank(remark)){
                criteria.and(Restrictions.where("remark").like(remark));
            }

            List<Client> list  = mapper.find(criteria);
            long         total = mapper.count(criteria);

            return new Page<Client>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Client> find(String accountId, String name, String wxno, String remark){
        try{
            Criteria criteria = new Criteria();
            criteria.orderBy(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(accountId)){
                criteria.and(Restrictions.where("accountId").eq(accountId));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.where("name").like(name));
            }
            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.where("wxno").like(wxno));
            }
            if(StringUtils.isNotBlank(remark)){
                criteria.and(Restrictions.where("remark").like(remark));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Client getById(String id){
        try{
            Assert.hasText(id, "客户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean existByAccountIdAndWxno(String accountId, String wxno){
        try{
            Assert.hasText(accountId, "关联的账户ID不能为空");
            Assert.hasText(wxno, "客户微信号不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("accountId").eq(accountId));
            criteria.and(Restrictions.where("wxno").eq(wxno));

            return (mapper.count(criteria) > 0);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
