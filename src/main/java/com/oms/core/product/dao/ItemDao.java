package com.oms.core.product.dao;

import com.oms.core.product.dao.mapper.ItemMapper;
import com.oms.core.product.entity.Item;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class ItemDao{

    private Logger logger = LoggerFactory.getLogger(ItemDao.class);

    @Autowired
    private ItemMapper mapper;

    public Page<Item> page(PageRequest pageRequest, String itemCode, String itemName, Integer minNum, Integer maxNum,
                           Integer minSellNum, Integer maxSellNum){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("itemCode"));

            if(StringUtils.isNotBlank(itemCode)){
                criteria.and(Restrictions.like("itemCode", itemCode));
            }
            if(StringUtils.isNotBlank(itemName)){
                criteria.and(Restrictions.like("itemName", itemName));
            }
            if(minNum != null){
                criteria.and(Restrictions.ge("num", minNum));
            }
            if(maxNum != null){
                criteria.and(Restrictions.le("num", maxNum));
            }
            if(minSellNum != null){
                criteria.and(Restrictions.ge("sellNum", minSellNum));
            }
            if(maxSellNum != null){
                criteria.and(Restrictions.le("sellNum", maxSellNum));
            }

            List<Item> list  = mapper.find(criteria);
            Long       total = mapper.count(criteria);

            return new Page<Item>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
