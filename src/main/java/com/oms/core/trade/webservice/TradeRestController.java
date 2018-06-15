package com.oms.core.trade.webservice;

import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.entity.Trade;
import com.oms.core.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/1.0/trade")
public class TradeRestController{

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Trade>> page(Long pageNum, Long pageSize, String wxno, String tradeId, ExpressType express, PayType payType,
                                       Long prepaidFee, Long price, Date minDateCreated, Date maxDateCreated, Date minAppointDeliveryTime,
                                       Date maxAppointDeliveryTime, String contactName, String phone, String province, String city,
                                       String district, String sellerName, String wlnumber, String prodName){

        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Trade> page = tradeService.page(pageRequest, wxno, tradeId, express, payType, prepaidFee, price, minDateCreated, maxDateCreated,
                                             minAppointDeliveryTime, maxAppointDeliveryTime, contactName, phone, province,
                                             city, district, sellerName, wlnumber, prodName);

        return new Responsed<Page<Trade>>("查询成功", page);
    }
}
