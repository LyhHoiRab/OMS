package com.oms.core.trade.service;

import com.oms.core.trade.dao.LogisticsModifyRecordDao;
import com.oms.core.trade.entity.LogisticsModifyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;

@Service
public class LogisticsModifyRecordServiceImpl implements LogisticsModifyRecordService{

    @Autowired
    private LogisticsModifyRecordDao logisticsModifyRecordDao;

    @Override
    public Page<LogisticsModifyRecord> page(PageRequest pageRequest, String tradeId, Date minOperateTime, Date maxOperateTime){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return logisticsModifyRecordDao.page(pageRequest, tradeId, minOperateTime, maxOperateTime);
    }
}
