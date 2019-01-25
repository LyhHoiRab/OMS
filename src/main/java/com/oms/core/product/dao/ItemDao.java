package com.oms.core.product.dao;

import com.oms.commons.consts.ProductType;
import com.oms.commons.consts.helper.ProductTypeHelper;
import com.oms.core.product.dao.mapper.ItemMapper;
import com.oms.core.product.entity.Item;
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
public class ItemDao{

    private Logger logger = LoggerFactory.getLogger(ItemDao.class);

    @Autowired
    private ItemMapper mapper;

    public void saveList(List<Item> list){
        try{
            Assert.notEmpty(list, "商品列表不能为空");

            final Date now = new Date();

            for(Item item : list){
                Assert.notNull(item, "商品信息不能为空");
                Assert.hasText(item.getCode(), "商品编码不能为空");

                item.setId(IDUtils.uuid32());
                item.setSales(0);
                item.setIsCheck(false);
                item.setCreateTime(now);
            }

            mapper.saveList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateList(List<Item> list){
        try{
            Assert.notEmpty(list, "商品列表不能为空");

            final Date now = new Date();

            for(Item item : list){
                Assert.notNull(item, "商品信息不能为空");
                Assert.hasText(item.getCode(), "商品编码不能为空");

                item.setUpdateTime(now);
            }

            mapper.updateList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Item> find(){
        try{
            return mapper.find(null);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Item> page(PageRequest pageRequest, String code, String name, String productName, ProductType type){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest));
            criteria.orderBy(Restrictions.asc("i.code"));

            if(StringUtils.isNotBlank(code)){
                criteria.and(Restrictions.where("i.code").like(code));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.where("i.name").like(name));
            }
            if(StringUtils.isNotBlank(productName)){
                criteria.and(Restrictions.where("p.name").like(productName));
            }
            if(type != null){
                criteria.and(Restrictions.where("p.type").eq(type, new ProductTypeHelper()));
            }

            List<Item> list  = mapper.findWithProduct(criteria);
            long       total = mapper.countWithProduct(criteria);

            return new Page<Item>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
