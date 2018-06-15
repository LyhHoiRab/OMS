package com.oms.core.trade.service;

import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.entity.Trade;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;

public interface TradeService{

    Page<Trade> page(PageRequest pageRequest, String wxno, String tradeId, ExpressType express, PayType payType,
                     Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                     Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                     String district, String sellerName, String wlnumber, String prodName);
}
