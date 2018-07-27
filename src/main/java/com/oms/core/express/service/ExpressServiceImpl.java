package com.oms.core.express.service;

import com.oms.core.express.dao.ExpressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExpressServiceImpl implements ExpressService{

    @Autowired
    private ExpressDao expressDao;

    @Override
    @Transactional
    public void change(List<String> ids, Integer expressId){
        Assert.notNull(expressId, "快递公司ID不能为空");

        if(ids != null && !ids.isEmpty()){
            expressDao.update(ids, expressId);
        }
    }
}
