package com.oms.core.popularize.dao;

import com.oms.core.popularize.dao.mapper.WxInfoMapper;
import com.oms.core.popularize.entity.WxInfo;
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
public class WxInfoDao{

    private Logger logger = LoggerFactory.getLogger(WxInfoDao.class);

    @Autowired
    private WxInfoMapper mapper;

    public void saveOrUpdate(WxInfo wxInfo){
        try{
            Assert.notNull(wxInfo, "微信信息不能为空");

            if(StringUtils.isBlank(wxInfo.getId())){
                Assert.hasText(wxInfo.getWxno(), "微信号不能为空");

                wxInfo.setId(IDUtils.uuid32());
                wxInfo.setCreateTime(new Date());
                mapper.save(wxInfo);
            }else{
                wxInfo.setUpdateTime(new Date());
                mapper.update(wxInfo);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public WxInfo getById(String id){
        try{
            Assert.hasText(id, "微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.where("id").eq(id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<WxInfo> page(PageRequest pageRequest, String wxno, String nickname, String phone, String remark, String sales){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest));
            criteria.orderBy(Restrictions.desc("w.wxno"));

            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.where("w.wxno").like(wxno));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.where("w.nickname").like(nickname));
            }
            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.where("w.phone").like(phone));
            }
            if(StringUtils.isNotBlank(remark)){
                criteria.and(Restrictions.where("w.remark").like(remark));
            }
            if(StringUtils.isNotBlank(sales)){
                criteria.and(Restrictions.where("s.nickname").like(sales));
            }

            List<WxInfo> list  = mapper.findWithSales(criteria);
            long         total = mapper.countWithSales(criteria);

            return new Page<WxInfo>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
