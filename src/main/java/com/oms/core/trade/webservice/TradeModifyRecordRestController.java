package com.oms.core.trade.webservice;

import com.oms.core.trade.entity.TradeModifyRecord;
import com.oms.core.trade.service.TradeModifyRecordService;
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
@RequestMapping(value = "/api/1.0/trade/modify")
public class TradeModifyRecordRestController{

    @Autowired
    private TradeModifyRecordService tradeModifyRecordService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<TradeModifyRecord>> page(Long pageNum, Long pageSize, String tradeId, Date minOperateTime, Date maxOperateTime){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<TradeModifyRecord> page = tradeModifyRecordService.page(pageRequest, tradeId, minOperateTime, maxOperateTime);

        return new Responsed<Page<TradeModifyRecord>>("查询成功", page);
    }
}
