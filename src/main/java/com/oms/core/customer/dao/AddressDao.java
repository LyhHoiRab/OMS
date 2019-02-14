package com.oms.core.customer.dao;

import com.oms.core.customer.dao.mapper.AddressMapper;
import com.oms.core.customer.entity.Address;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
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
public class AddressDao{

    private Logger logger = LoggerFactory.getLogger(AddressDao.class);

    @Autowired
    private AddressMapper mapper;

    public void saveOrUpdate(Address address){
        try{
            Assert.notNull(address, "客户收货地址信息不能为空");

            if(StringUtils.isBlank(address.getId())){
                Assert.hasText(address.getClientId(), "客户ID不能为空");
                Assert.hasText(address.getCountry(), "地址国家不能为空");
                Assert.hasText(address.getProvince(), "地址省份不能为空");
                Assert.hasText(address.getCity(), "地址城市/区不能为空");
                Assert.hasText(address.getPhone(), "联系方式不能为空");
                Assert.hasText(address.getContact(), "收件人不能为空");

                address.setId(IDUtils.uuid32());
                address.setCreateTime(new Date());
                mapper.save(address);
            }else{
                address.setUpdateTime(new Date());
                mapper.update(address);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateIsDefaultById(String id, Boolean isDefault){
        try{
            Assert.hasText(id, "收货地址ID不能为空");
            Assert.notNull(isDefault, "收货地址默认状态不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            mapper.updateIsDefault(isDefault, criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateIsDefaultByClientId(String clientId, Boolean isDefault){
        try{
            Assert.hasText(clientId, "客户ID不能为空");
            Assert.notNull(isDefault, "收货地址默认状态不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("clientId").eq(clientId));

            mapper.updateIsDefault(isDefault, criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Address getById(String id){
        try{
            Assert.hasText(id, "地址信息ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Address> findByClientId(String clientId){
        try{
            Assert.hasText(clientId, "客户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.orderBy(Restrictions.desc("isDefault"));
            criteria.and(Restrictions.where("clientId").eq(clientId));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean hasByClientId(String clientId){
        try{
            Assert.hasText(clientId, "客户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("clientId").eq(clientId));

            return (mapper.count(criteria) > 0);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
