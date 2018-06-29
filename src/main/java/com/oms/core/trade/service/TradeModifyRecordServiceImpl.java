package com.oms.core.trade.service;

import com.oms.core.trade.dao.TradeModifyRecordDao;
import com.oms.core.trade.entity.TradeModifyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;

@Service
public class TradeModifyRecordServiceImpl implements TradeModifyRecordService{

    @Autowired
    private TradeModifyRecordDao tradeModifyRecordDao;

    public Page<TradeModifyRecord> page(PageRequest pageRequest, String tradeId, Date minOperateTime, Date maxOperateTime){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return tradeModifyRecordDao.page(pageRequest, tradeId, minOperateTime, maxOperateTime);
    }
}
