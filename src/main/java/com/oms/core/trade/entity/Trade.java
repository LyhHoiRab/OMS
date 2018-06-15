package com.oms.core.trade.entity;

import com.oms.core.product.entity.BoxProduct;
import com.oms.core.profile.entity.Profile;
import com.oms.core.trade.consts.ExpressType;
import com.oms.core.trade.consts.PayType;
import com.oms.core.trade.consts.StatusType;
import com.oms.core.trade.consts.TradeType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * table: trade
 */
@Getter
@Setter
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
    //微信号
    private String wxno;
    //下单微信号
    private WechatInfo wechatInfo;
    //物流信息ID
    private Long consigneeId;
    //物流信息
    private Logistics logistics;
    //盒子ID
    private Long boxId;
    //订单产品
    private List<BoxProduct> products;
    //销售ID
    private Long pUserId;
    //销售
    private Profile seller;
    //物流状况
    private Order order;
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
