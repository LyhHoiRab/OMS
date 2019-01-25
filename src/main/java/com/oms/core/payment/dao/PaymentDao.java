package com.oms.core.payment.dao;

import com.oms.commons.consts.helper.UsingStatusHelper;
import com.oms.core.payment.dao.mapper.PaymentMapper;
import com.oms.core.payment.entity.Payment;
import com.wah.doraemon.domain.consts.UsingStatus;
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
public class PaymentDao{

    private Logger logger = LoggerFactory.getLogger(PaymentDao.class);

    @Autowired
    private PaymentMapper mapper;

    public void saveOrUpdate(Payment payment){
        try{
            Assert.notNull(payment, "付款方式信息不能为空");

            if(StringUtils.isBlank(payment.getId())){
                Assert.notNull(payment.getStatus(), "付款方式可用状态不能为空");

                payment.setId(IDUtils.uuid32());
                payment.setCreateTime(new Date());
                mapper.save(payment);
            }else{
                payment.setUpdateTime(new Date());
                mapper.update(payment);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Payment getById(String id){
        try{
            Assert.hasText(id, "付款方式ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Payment> page(PageRequest pageRequest, String description, UsingStatus status){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest));
            criteria.orderBy(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(description)){
                criteria.and(Restrictions.where("description").like(description));
            }
            if(status != null){
                criteria.and(Restrictions.where("status").eq(status, new UsingStatusHelper()));
            }

            List<Payment> list  = mapper.find(criteria);
            long          total = mapper.count(criteria);

            return new Page<Payment>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
