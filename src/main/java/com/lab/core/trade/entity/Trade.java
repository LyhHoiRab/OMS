package com.lab.core.trade.entity;

import com.lab.core.trade.consts.ExpressType;
import com.lab.core.trade.consts.PayType;
import com.lab.core.trade.consts.StatusType;
import com.lab.core.trade.consts.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Trade{

    private Long id;
    //订单号
    private String tradeId;
    //订单标题
    private String title;
    //订单状态
    private StatusType status;
    //订单类型
    private TradeType tradeType;
    //付款类型
    private PayType payType;
    //订单原价
    private Long price;
    //订单实付
    private Long totalFee;
    //订单预付
    private Long prepaidFee;
    //快递类型
    private ExpressType expressType;
    //下单微信号
    private String wxNo;
    //物流信息
    private Logistics logistics;
    //订单产品
    private List<BoxProduct> box;
    //销售
    private Seller seller;
    //交易备注
    private String tradeMemo;
    //确认收货时间
    private Date takeOverTime;
    //单号上传时间
    private Date orderNumberTime;
    //发货时间
    private Date appointDeliveryTime;
    //下单时间
    private Date dateCreated;
    //更新时间
    private Date lastUpdated;
}
