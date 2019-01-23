package com.oms.core.product.dao;

import com.oms.core.product.dao.mapper.ProductMapper;
import com.oms.core.product.entity.Product;
import com.wah.doraemon.security.exception.DataAccessException;
import com.wah.doraemon.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Repository
public class ProductDao{

    private Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    private ProductMapper mapper;

    public void saveOrUpdate(Product product){
        try{
            Assert.notNull(product, "产品信息不能为空");

            if(StringUtils.isBlank(product.getId())){
                Assert.hasText(product.getCode(), "产品编码不能为空");
                Assert.notNull(product.getCustomized(), "定制类型不能为空");
                Assert.notNull(product.getType(), "产品类型不能为空");

                product.setId(IDUtils.uuid32());
                product.setCreateTime(new Date());
                mapper.save(product);
            }else{
                product.setUpdateTime(new Date());
                mapper.update(product);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(List<Product> list){
        try{
            Assert.notEmpty(list, "产品列表不能为空");

            final Date now = new Date();

            for(Product product : list){
                Assert.hasText(product.getCode(), "产品编码不能为空");
                Assert.notNull(product.getCustomized(), "定制类型不能为空");
                Assert.notNull(product.getType(), "产品类型不能为空");

                product.setId(IDUtils.uuid32());
                product.setCreateTime(now);
            }

            mapper.saveList(list);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
