package com.oms.core.trade.entity;

import com.oms.core.trade.consts.OrderStateType;
import com.oms.core.trade.consts.OrderStatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: miku_orders_logistics
 */
@Getter
@Setter
public class Order{

    private Long            id;
    //订单号
    private String          tradeId;
    //物流公司名称
    private String          wlcompany;
    //物流单号
    private String          wlnumber;
    //物流公司简码
    private String          wlsnumber;
    //物流信息
    private String          logicSpecificAddr;
    //物流状态
    private OrderStatusType status;
    //请求状态
    private OrderStateType  state;
    //创建时间
    private Date            dateCreated;
    //更新时间
    private Date            lastUpdated;
}
