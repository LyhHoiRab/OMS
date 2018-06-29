package com.oms.core.trade.service;

import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.entity.Trade;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;
import java.util.List;

public interface TradeService{

    Page<Trade> page(PageRequest pageRequest, String wxno, String tradeId, ExpressType express, PayType payType,
                     Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                     Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                     String district, String sellerName, String wlnumber, String prodName);

    Page<Trade> pageByAppointDeliveryTimeNull(PageRequest pageRequest, String wxno, String tradeId, PayType payType,
                                              String sellerName, Date minDateCreated, Date maxDateCreated, String contactName,
                                              String phone);

    Page<Trade> pageByStatusFailAndUnusual(PageRequest pageRequest, String wxno, String tradeId, PayType payType,
                                           String sellerName, Date minDateCreated, Date maxDateCreated, String contactName,
                                           String phone);

    XSSFWorkbook export(String wxno, String tradeId, ExpressType express, PayType payType, Long prepaidFee, Long price,
                        Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime, Date maxAppointDeliveryTime,
                        String contactName, String phone, String province, String city, String district, String sellerName,
                        String wlnumber, String prodName);
}
