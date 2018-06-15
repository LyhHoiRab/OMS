package com.oms.core.trade.dao;

import com.oms.core.trade.dao.mapper.WechatInfoMapper;
import com.oms.core.trade.entity.WechatInfo;
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
public class WechatInfoDao{

    private Logger logger = LoggerFactory.getLogger(WechatInfoDao.class);

    @Autowired
    private WechatInfoMapper mapper;

    public List<WechatInfo> findByWxNos(List<String> wxNos){
        try{
            Assert.notEmpty(wxNos, "微信号列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("wxNo", wxNos));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<WechatInfo> find(String wxName, String wxNo, List<String> sellerIds){
        try{
            Criteria criteria = new Criteria();

            if(StringUtils.isNotBlank(wxName)){
                criteria.and(Restrictions.like("wxName", wxName));
            }
            if(StringUtils.isNotBlank(wxNo)){
                criteria.and(Restrictions.like("wxNo", wxNo));
            }
            if(sellerIds != null && !sellerIds.isEmpty()){
                criteria.and(Restrictions.in("userId", sellerIds));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
