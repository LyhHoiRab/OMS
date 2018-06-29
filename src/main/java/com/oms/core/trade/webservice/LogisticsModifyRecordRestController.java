package com.oms.core.trade.webservice;

import com.oms.core.trade.entity.LogisticsModifyRecord;
import com.oms.core.trade.service.LogisticsModifyRecordService;
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
@RequestMapping(value = "/api/1.0/logistics/modify")
public class LogisticsModifyRecordRestController{

    @Autowired
    private LogisticsModifyRecordService logisticsModifyRecordService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<LogisticsModifyRecord>> page(Long pageNum, Long pageSize, String tradeId, Date minOperateTime, Date maxOperateTime){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<LogisticsModifyRecord> page = logisticsModifyRecordService.page(pageRequest, tradeId, minOperateTime, maxOperateTime);

        return new Responsed<Page<LogisticsModifyRecord>>("查询成功", page);
    }
}
