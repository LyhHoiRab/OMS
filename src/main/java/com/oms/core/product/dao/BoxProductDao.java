package com.oms.core.product.dao;

import com.oms.core.product.dao.mapper.BoxProductMapper;
import com.oms.core.product.entity.BoxProduct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class BoxProductDao{

    private Logger logger = LoggerFactory.getLogger(BoxProductDao.class);

    @Autowired
    private BoxProductMapper mapper;

    public List<BoxProduct> findByBoxIds(List<Long> boxIds){
        try{
            Assert.notEmpty(boxIds, "盒子ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("b.boxId", boxIds));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<BoxProduct> find(List<Long> boxIds, String prodName){
        try{
            Criteria criteria = new Criteria();

            if(boxIds != null && !boxIds.isEmpty()){
                criteria.and(Restrictions.in("b.boxId", boxIds));
            }
            if(StringUtils.isNotBlank(prodName)){
                criteria.and(Restrictions.like("p.prodName", prodName));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
