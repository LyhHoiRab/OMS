package com.oms.core.trade.service;

import com.oms.core.trade.entity.TradeModifyRecord;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;

public interface TradeModifyRecordService{

    Page<TradeModifyRecord> page(PageRequest pageRequest, String tradeId, Date minOperateTime, Date maxOperateTime);
}
