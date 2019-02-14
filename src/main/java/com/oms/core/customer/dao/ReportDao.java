package com.oms.core.customer.dao;

import com.oms.core.customer.dao.mapper.ReportMapper;
import com.oms.core.customer.entity.Report;
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
public class ReportDao{

    private Logger logger = LoggerFactory.getLogger(ReportDao.class);

    @Autowired
    private ReportMapper mapper;

    public void saveOrUpdate(Report report){
        try{
            Assert.notNull(report, "分析报告信息不能为空");

            if(StringUtils.isBlank(report.getId())){
                Assert.hasText(report.getClientId(), "客户ID不能为空");

                report.setId(IDUtils.uuid32());
                report.setCreateTime(new Date());
                mapper.save(report);
            }else{
                report.setUpdateTime(new Date());
                mapper.update(report);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Report getById(String id){
        try{
            Assert.hasText(id, "分析报告ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Report> findByClientId(String clientId){
        try{
            Assert.hasText(clientId, "客户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("clientId").eq(clientId));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

