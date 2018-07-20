package com.oms.core.profile.dao;

import com.oms.core.profile.dao.mapper.ProfileMapper;
import com.oms.core.profile.entity.Profile;
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
public class ProfileDao{

    private Logger logger = LoggerFactory.getLogger(ProfileDao.class);

    @Autowired
    private ProfileMapper mapper;

    public List<Profile> findByIds(List<Long> ids){
        try{
            Assert.notEmpty(ids, "销售ID列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("id", ids));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Profile> find(String realName, List<String> ids){
        try{
            Criteria criteria = new Criteria();

            if(StringUtils.isNotBlank(realName)){
                criteria.and(Restrictions.like("realName", realName));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Profile> findCsad(String realName){
        try{
            return mapper.findCsad(realName);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Profile> pageBySellers(PageRequest pageRequest, String realName){
        try{
            Criteria criteria = new Criteria();

            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.asc("realName"));

            criteria.and(Restrictions.eq("isExpert", true));

            if(StringUtils.isNotBlank(realName)){
                criteria.and(Restrictions.like("realName", realName));
            }

            List<Profile> list  = mapper.find(criteria);
            Long          total = mapper.count(criteria);

            return new Page<Profile>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
