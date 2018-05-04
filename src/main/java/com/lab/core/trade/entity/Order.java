package com.lab.core.trade.entity;

import com.lab.core.trade.consts.OrderStateType;
import com.lab.core.trade.consts.OrderStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order{

    private Long id;
    //物流信息
    private Long logisticsId;
    //订单号
    private String tradeId;
    //物流公司名称
    private String wlcompany;
    //物流单号
    private String wlnumber;
    //物流公司简码
    private String wlsnumber;
    //物流信息
    private String logicSpecificAddr;
    //物流状态
    private OrderStateType stateType;
    //请求状态
    private OrderStatusType statusType;
}
