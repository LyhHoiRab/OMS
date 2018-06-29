package com.oms.core.trade.entity;

import com.oms.core.trade.consts.PayType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: trade_modifi_logs
 */
@Getter
@Setter
public class TradeModifyRecord{

    private Long    id;
    private String  tradeId;
    private Long    originalPrice;
    private Long    modifiPrice;
    private PayType originalPayType;
    private PayType modifiPayType;
    private Long    originalPrepaidFee;
    private Long    modifiPrepaidFee;
    private String  originalMemo;
    private String  modifiMemo;
    private Date    originalDateCreated;
    private Date    modifiDateCreated;
    private String  operator;
    private Date    operateTime;
    private Date    createTime;
    private Date    updateTime;
}
